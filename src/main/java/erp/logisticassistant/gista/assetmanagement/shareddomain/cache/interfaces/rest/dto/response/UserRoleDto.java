package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleDto extends AuditableCacheDto{
  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("composite_role")
  private CompositeRoleDto compositeRole;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("start_date")
  private String startDate;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("end_date")
  private String endDate;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("enabled")
  private Boolean enabled;
}
