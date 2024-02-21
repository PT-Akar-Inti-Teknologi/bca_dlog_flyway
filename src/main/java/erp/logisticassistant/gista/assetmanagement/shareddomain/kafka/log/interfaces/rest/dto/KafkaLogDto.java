package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaLogDto {
    
    @JsonProperty("transaction_type_id")
    private String trxTypeId;

    @JsonProperty("key")
    private String key;

     @JsonProperty("topic")
     private String topic;
  
    @JsonProperty("action")
    private String action;
  
    @JsonProperty("document_no")
    private String documentNo;

     @JsonProperty("payload")
     private String payload;

     @JsonProperty("log")
     private String log;
}
