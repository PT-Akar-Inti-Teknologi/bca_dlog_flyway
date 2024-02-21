package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.application.internal.command;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.EntityKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.infrastructure.KafkaLogRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages.KafkaProducerService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaConsumerRecordDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaLogDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.transform.KafkaLogTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.service.CallbackBeanFactory;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ImplInternalCommandKafkaLogService {

    private final ConvertTransform convertTransform;
    private final KafkaLogRepository kafkaLogRepository;
    private final KafkaLogTransform kafkaLogTransform;
    private final CallbackBeanFactory callbackBeanFactory;
    private final KafkaProducerService kafkaProducerService;
    private final ApplicationProperties applicationProperties; 


    @PostConstruct
    public void init() {
        kafkaProducerService.setKafkaProducerService(this);
    }

    public void doCreateLog(Object data, EnumKafkaLog event, String exception, String classConsumer) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        KafkaConsumerRecordDto consumerRecordDto = new KafkaConsumerRecordDto();
        KafkaLogDto workflowDto = new KafkaLogDto();
        try {
            consumerRecordDto = mapper.convertValue(data, KafkaConsumerRecordDto.class);
            workflowDto = mapper.readValue(consumerRecordDto.getValue(), KafkaLogDto.class);

            kafkaLogRepository.save(kafkaLogTransform.toCreateLog(consumerRecordDto, workflowDto, event, exception, workflowDto.getDocumentNo(), classConsumer));
        } catch (Exception e) {
            log.info("Kafka Log service failed to convert value : " + e);
        }
    }

    public GlobalResponse doRetry(Long id) {
        Optional<EntityKafkaLog> entityKafkaLog = kafkaLogRepository.findById(id);
        if (!entityKafkaLog.isPresent())
            throw new GlobalException(StatusCode.NOT_FOUND, GlobalMessage.Core.ERROR_MEESAGE_KAFKALOG_NOT_FOUND);

        try {
            if (entityKafkaLog.get().getEvent().equals(EnumKafkaLog.CONSUME)){

                callbackBeanFactory.doRetryKafka(entityKafkaLog.get().getClassConsumer(), entityKafkaLog.get().getPayload());
                entityKafkaLog.get().setIsResolved(true);
                kafkaLogRepository.save(entityKafkaLog.get());

            } else {
                if (Boolean.TRUE.equals(isMaintenanceMode()))
                    throw new GlobalException(StatusCode.FORBIDDEN, GlobalMessage.Core.ERROR_MAINTENANCE_MODE);

                kafkaProducerService.produceMessage(entityKafkaLog.get().getKey(), entityKafkaLog.get().getTopic(), entityKafkaLog.get().getPayload());
                entityKafkaLog.get().setIsResolved(true);
                kafkaLogRepository.save(entityKafkaLog.get());
            }

        } catch (Exception e) {
            log.info("failed to doRetry kafka : " + e);
            throw new GlobalException(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return new GlobalResponse(
                convertTransform.getTimestamp(LocalDateTime.now()),
                StatusCode.OK.getHttpStatus().value(),
                StatusCode.OK.getCodeDesc(),
                StatusCode.OK,
                StatusCode.OK.getMessage(),
                id);
    }

    public void doCreateProducerLog(String key, String topic, String payload, String exception) {
        try {
            EntityKafkaLog kafkaLog = kafkaLogTransform.toCreateProducerLog(key, topic, EnumKafkaLog.PRODUCE, exception, payload);
            kafkaLogRepository.save(kafkaLog);
        } catch (Exception e) {
            log.info("Kafka Log service failed to doCreateProducerLog : " + e);
        }
    }

    public void doCreateLog(KafkaConsumerException exception, EnumKafkaLog event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        KafkaLogDto kafkaLogDto = new KafkaLogDto();
        EntityKafkaLog entity = new EntityKafkaLog();
        try {
            kafkaLogDto = kafkaLogTransform.toKafkaLogFromException(exception);
            entity = kafkaLogTransform.toCreateLog(exception, kafkaLogDto, event);

            kafkaLogRepository.save(entity);
        } catch (Exception e) {
            log.info("Kafka Log service failed to convert value : " + e);
            kafkaProducerService.produceDeathLetterMessage(entity);
        }
    }

    public GlobalResponse processProduceAll() {

        if (Boolean.TRUE.equals(isMaintenanceMode()))
            throw new GlobalException(StatusCode.FORBIDDEN, GlobalMessage.Core.ERROR_MAINTENANCE_MODE);

        List<EntityKafkaLog> kafkaLogs = kafkaLogRepository.findAllByEventAndIsResolved(EnumKafkaLog.PRODUCE, false);
        try {
            for (EntityKafkaLog entityKafkaLog : kafkaLogs) {

                kafkaProducerService.produceMessage(entityKafkaLog.getKey(), entityKafkaLog.getTopic(), entityKafkaLog.getPayload());

                entityKafkaLog.setIsResolved(true);
                kafkaLogRepository.save(entityKafkaLog);
                
            }
        } catch (Exception e) {
            log.info("failed to doRetry kafka : " + e);
            throw new GlobalException(StatusCode.INTERNAL_SERVER_ERROR, GlobalMessage.Core.ERROR_INTERNAL_SERVER);
        }

        return new GlobalResponse(
            convertTransform.getTimestamp(LocalDateTime.now()),
            StatusCode.OK.getHttpStatus().value(),
            StatusCode.OK.getCodeDesc(),
            StatusCode.OK,
            StatusCode.OK.getMessage(), 
            kafkaLogs.size() + " messages has been produced");
    }

    private Boolean isMaintenanceMode(){
        return applicationProperties.getSpring().getMaintenanceMode();
    }
}
