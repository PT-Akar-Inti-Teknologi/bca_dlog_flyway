package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class BaseFileSyncResponse {
    
    @JsonProperty("id")
    private String id;

    @JsonProperty("succeed")
    private Boolean succeed;

    @JsonProperty("failed_cause")
    private String failedCause;
}
