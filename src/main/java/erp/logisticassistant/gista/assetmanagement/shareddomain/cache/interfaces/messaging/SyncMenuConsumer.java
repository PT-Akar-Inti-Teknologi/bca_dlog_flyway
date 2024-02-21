package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandMenuService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer.MenuConsumerDto;
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
@Component("SyncMenuConsumer")
public class SyncMenuConsumer implements ConsumerCommandService{
    private final CommandMenuService commandMenuService;
    private final ApplicationProperties applicationProperties;

    @KafkaListener(
            topics = "#{'${spring.kafka.consumer.topic.identity.menu}'}",
            groupId = "#{'${spring.kafka.consumer.topic.group-id}'}")
    public void consume(String message){
        log.info("Sync Menu {}", message);
        ObjectMapper mapper = new ObjectMapper();
        MenuConsumerDto dto = null;
        try {
            dto = mapper.readValue(message, MenuConsumerDto.class);
            if(Boolean.TRUE.equals(commandMenuService.checkProcessingData(dto.getData()))) {
                switch (dto.getAction()) {
                    case "CREATE":
                        commandMenuService.processCreateMenu(dto.getData());
                        break;
                    case "UPDATE":
                        commandMenuService.processUpdateMenu(dto.getData());
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.info("Failed to sync Menu : " + e);
            throw new KafkaConsumerException(this.getClass().getSimpleName(), e.toString(), 0, true, message,
                    applicationProperties.getSpring().getKafka().getConsumer().getTopic().getIdentity().getMenu(), dto == null ? "" : dto.getData().getMenuId());
        }

    }

}