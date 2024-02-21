package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.ApplicationConsumerDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages.ConsumerCommandService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Component("SyncApplicationConsumer")
public class SyncApplicationConsumer implements ConsumerCommandService{
    private final CommandApplicationService internalCommandApplicationService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.application}'}", 
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Application {}", message);
        ObjectMapper mapper = new ObjectMapper();
        ApplicationConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, ApplicationConsumerDto.class);
            switch (dto.getAction()){
                case "CREATE":
                    internalCommandApplicationService.processCreateApplication(dto.getData());
                    break;
                case "UPDATE":
                    internalCommandApplicationService.processUpdateApplication(dto.getData());
                    break;
                case "DELETE":
                    internalCommandApplicationService.processDeleteApplication(dto.getData());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.info("Failed to sync Application : " + e.getMessage());
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getApplication(), dto == null ? "" : dto.getData().getApplicationId());
        }
    }

}