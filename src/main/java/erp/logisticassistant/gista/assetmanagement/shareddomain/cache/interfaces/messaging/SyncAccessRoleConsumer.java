package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandAccessRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.AccessRoleConsumerDto;
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
@Component("SyncAccessRoleConsumer")
@AllArgsConstructor
public class SyncAccessRoleConsumer implements ConsumerCommandService{

    private final CommandAccessRoleService commandSyncAccessRoleService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.access-role}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Access Role {}", message);
        ObjectMapper mapper = new ObjectMapper();
        AccessRoleConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, AccessRoleConsumerDto.class);
            if(Boolean.TRUE.equals(commandSyncAccessRoleService.checkProcessingData(dto.getData()))) {
                switch (dto.getAction()) {
                    case "CREATE":
                        commandSyncAccessRoleService.processCreateAccessRole(dto.getData());
                        break;
                    case "UPDATE":
                        commandSyncAccessRoleService.processUpdateAccessRole(dto.getData());
                        break;
                    case "DELETE":
                        commandSyncAccessRoleService.processSoftDeleteAccessRole(dto.getData());
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.info("Failed to sync Access Role : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getAccessRole(), dto == null ? "" : dto.getData().getAccessId());
        }
    }
}