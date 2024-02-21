package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandAssosiateRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.AssosiateRoleConsumerDto;
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
@Component("SyncAssosiateRoleConsumer")
public class SyncAssosiateRoleConsumer implements ConsumerCommandService{

    private final CommandAssosiateRoleService commandAssosiateRoleService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.assosiate-role}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Assosiate Role {}", message);
        ObjectMapper mapper = new ObjectMapper();
        AssosiateRoleConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, AssosiateRoleConsumerDto.class);
            switch (dto.getAction()){
                case "CREATE":
                    commandAssosiateRoleService.processCreateAssosiateRole(dto.getData());
                    break;
                case "DELETE":
                    commandAssosiateRoleService.processDeleteAssosiateRole(dto.getData());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.info("Failed to sync Assosiate Role : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getAssosiateRole(),
                    dto == null ? "" : dto.getData().getCompositeRole().getCompositeRoleId() + dto.getData().getApplicationRole().getApplicationRoleId());
        }
    }

}