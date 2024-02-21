package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandCompositeRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.CompositeRoleConsumerDto;
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
@Component("SyncCompositeRoleConsumer")
public class SyncCompositeRoleConsumer implements ConsumerCommandService{

    private final CommandCompositeRoleService commandCompositeRoleService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.composite-role}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Composite Role {}", message);
        ObjectMapper mapper = new ObjectMapper();
        CompositeRoleConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, CompositeRoleConsumerDto.class);
            switch (dto.getAction()){
                case "CREATE":
                    commandCompositeRoleService.processCreateCompositeRole(dto.getData());
                    break;
                case "UPDATE":
                    commandCompositeRoleService.processUpdateCompositeRole(dto.getData());
                    break;
                case "DELETE":
                    commandCompositeRoleService.processDeleteCompositeRole(dto.getData());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.info("Failed to sync Composite Role : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getCompositeRole(), dto == null ? "" : dto.getData().getName());
        }
    }

}