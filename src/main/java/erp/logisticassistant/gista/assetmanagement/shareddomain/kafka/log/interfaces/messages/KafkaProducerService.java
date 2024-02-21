package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.application.internal.command.ImplInternalCommandKafkaLogService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.EntityKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.DeathLetterTopicDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaConsumerRecordDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.transform.KafkaLogTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService{
    
    private final KafkaTemplate kafkaTemplate;
    private final ApplicationProperties applicationProperties;
    private ImplInternalCommandKafkaLogService commandKafkaLogService;
    private final KafkaLogTransform kafkaLogTransform;

    public void setKafkaProducerService(ImplInternalCommandKafkaLogService commandKafkaLogService) {
        this.commandKafkaLogService = commandKafkaLogService;
    }

    @Async
    public void produceMessage(String key, String topic, String message){
        try {
           kafkaTemplate.send(topic, key, message);
        } catch (Exception e) {
            log.info(GlobalMessage.Core.ERROR_KAFKA,  e);
            commandKafkaLogService.doCreateProducerLog(key, topic, message, e.getMessage());
        }
    }

    public void produceRetry(Object data, int retryCount, String classConsumer, String key,  String payload){
        KafkaConsumerRecordDto consumerRecordDto = new KafkaConsumerRecordDto();
        String retryTopic = applicationProperties.getSpring().getKafka().getConsumer().getTopic().getRetry();
        try {
            consumerRecordDto = getMapper().convertValue(data, KafkaConsumerRecordDto.class);
            consumerRecordDto.setRetryCount(retryCount+1);
            consumerRecordDto.setClassConsumer(classConsumer);
            consumerRecordDto.setValue(payload);
            
            kafkaTemplate.send(retryTopic, key, getMapper().writeValueAsString(consumerRecordDto));
        } catch (Exception e) {
            log.info(GlobalMessage.Core.ERROR_KAFKA,  e);
            commandKafkaLogService.doCreateProducerLog(key, retryTopic, payload, e.getMessage());
        }
    }

    public void produceMessageFromLog(String topic, String message){
        try {
           kafkaTemplate.send(topic,message);
        } catch (Exception e) {
            log.info(GlobalMessage.Core.ERROR_KAFKA,  e);
            throw new KafkaProducerException(topic, "failed to produce message");
        }
    }

    public void produceDeathLetterMessage(EntityKafkaLog entity){
        try {
            String retryTopic = applicationProperties.getSpring().getKafka().getConsumer().getTopic().getRetry();
            DeathLetterTopicDto deathLetterDto = kafkaLogTransform.toCreateDeathLetterTopic(entity, retryTopic);
            kafkaTemplate.send(applicationProperties.getSpring().getKafka().getProducer().getDeathLetterTopic(),
                    getMapper().writeValueAsString(deathLetterDto));
        } catch (Exception e) {
            log.info(GlobalMessage.Core.ERROR_KAFKA,  e);
        }
    }

    private final ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return mapper;
    }
}
