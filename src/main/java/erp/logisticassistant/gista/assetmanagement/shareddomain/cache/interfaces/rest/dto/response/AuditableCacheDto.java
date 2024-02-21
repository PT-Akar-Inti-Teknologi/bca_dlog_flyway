package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuditableCacheDto {

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("modified_by")
    private String modifiedBy;

    @JsonProperty("modified_at")
    private String modifiedAt;

    @JsonProperty("enabled")
    private Boolean enabled;
}
