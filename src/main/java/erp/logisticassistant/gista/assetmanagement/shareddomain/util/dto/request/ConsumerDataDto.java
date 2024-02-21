package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerDataDto {
    
    @JsonProperty("user_id")
    private String userId;
  
    @JsonProperty("username")
    private String username;
  
    @JsonProperty("sequence")
    private Integer sequence;
  
    @JsonProperty("action")
    private String workflowStatus;
}
