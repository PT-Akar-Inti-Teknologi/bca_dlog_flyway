package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssosiateRoleDto extends AuditableCacheDto{
  @JsonProperty("application_role")
  private ApplicationRoleDto applicationRole;

  @JsonProperty("composite_role")
  private CompositeRoleDto compositeRole;
}
