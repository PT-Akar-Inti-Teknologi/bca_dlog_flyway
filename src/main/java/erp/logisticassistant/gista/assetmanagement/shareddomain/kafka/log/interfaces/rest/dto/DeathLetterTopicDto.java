package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeathLetterTopicDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("event")
    private EnumKafkaLog event;

    @JsonProperty("action")
    private String action;

    @JsonProperty("class_consumer")
    private String classConsumer;

    @JsonProperty("description")
    private String description;

    @JsonProperty("is_resolved")
    private Boolean isResolved;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("log")
    private String log;

    @JsonProperty("retry_topic")
    private String retryTopic;
}
