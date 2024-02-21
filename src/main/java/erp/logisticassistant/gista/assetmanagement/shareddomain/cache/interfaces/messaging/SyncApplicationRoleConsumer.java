package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandApplicationRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.ApplicationRoleConsumerDto;
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
@Component("SyncApplicationRoleConsumer")
public class SyncApplicationRoleConsumer implements ConsumerCommandService{

    private final CommandApplicationRoleService internalCommandApplicationRoleService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.application-role}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Application Role {}", message);
        ObjectMapper mapper = new ObjectMapper();
        ApplicationRoleConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, ApplicationRoleConsumerDto.class);
            switch (dto.getAction()){
                case "CREATE":
                    internalCommandApplicationRoleService.processCreateApplicationRole(dto.getData());
                    break;
                case "UPDATE":
                    internalCommandApplicationRoleService.processUpdateApplicationRole(dto.getData());
                    break;
                case "DELETE":
                    internalCommandApplicationRoleService.processDeleteApplicationRole(dto.getData());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.info("Failed to sync Application Role : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getApplicationRole(), dto == null ? "" : dto.getData().getName());
        }
    }

}