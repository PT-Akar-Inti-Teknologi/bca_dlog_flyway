package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KafkaConsumerRecordDto {
    
    @JsonProperty("topic")
    private String topic;

    @JsonProperty("value")
    private String value;

    @JsonProperty("key")
    private String key;

    @JsonProperty("partition")
    private String partition;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("class_consumer")
    private String classConsumer;

    @JsonProperty("retry_count")
    private int retryCount;
}
