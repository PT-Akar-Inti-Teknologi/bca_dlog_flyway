package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuditableCacheDto {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("version")
    private Integer version;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("created_by")
    private String createdBy;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("modified_by")
    private String modifiedBy;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("modified_at")
    private String modifiedAt;
}
