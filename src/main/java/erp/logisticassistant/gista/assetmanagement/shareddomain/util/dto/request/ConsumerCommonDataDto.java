package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerCommonDataDto {
    
    @JsonProperty("document_no")
    private String documentNo;

    @JsonProperty("status")
    private String documentStatus;

    @JsonProperty("url")
    private String url;

    @JsonProperty("description")
    private String description;
}
