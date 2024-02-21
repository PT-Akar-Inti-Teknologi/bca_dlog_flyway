package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandUserService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.UserConsumerDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages.ConsumerCommandService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@Component("SyncUserConsumer")
public class SyncUserConsumer implements ConsumerCommandService {

    private final CommandUserService commandUserService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.user}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync User {}", message);
        ObjectMapper mapper = new ObjectMapper();
        UserConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, UserConsumerDto.class);
            if(Boolean.TRUE.equals(commandUserService.checkProcessingData(dto.getData()))) {
                switch (dto.getAction()) {
                    case "CREATE":
                        commandUserService.processCreateSyncUser(dto.getData());
                        break;
                    case "UPDATE":
                        commandUserService.processUpdateSyncUser(dto.getData());
                        break;
                    case "DELETE":
                        commandUserService.processSoftDeleteSyncUser(dto.getData());
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.info("Failed to sync User : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getUser(), dto == null ? "" : dto.getData().getUsername());
        }
    }

}