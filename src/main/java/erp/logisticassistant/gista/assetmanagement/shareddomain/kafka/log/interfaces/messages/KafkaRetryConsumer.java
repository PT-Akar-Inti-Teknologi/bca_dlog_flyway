package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaConsumerRecordDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.service.CallbackBeanFactory;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaRetryConsumer{

    private final ApplicationProperties applicationProperties;
    private final CallbackBeanFactory callbackBeanFactory;

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.retry}'}", groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    private void consume(String message){
        KafkaConsumerRecordDto consumerRecordDto = new KafkaConsumerRecordDto();
        try {
            ObjectMapper mapper = new ObjectMapper();
            consumerRecordDto = mapper.readValue(message, KafkaConsumerRecordDto.class);
            callbackBeanFactory.doRetryKafka(consumerRecordDto.getClassConsumer(), consumerRecordDto.getValue());
        } catch (KafkaConsumerException e) {
            log.info("kafka consume error on : " + e);
            throw new KafkaConsumerException(e.getClassName(), e.getMessage(), consumerRecordDto.getRetryCount(), e.getTechnicalFailure(), e.getPayload(), e.getTopic(), e.getKey());
        } catch (Exception e) {
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), consumerRecordDto.getRetryCount(), true, consumerRecordDto.getValue(),
                        applicationProperties.getSpring().getKafka().getConsumer().getTopic().getRetry(), consumerRecordDto.getKey());
        }
    }

}
