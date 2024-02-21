package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationRoleDto extends AuditableCacheDto{
  @JsonProperty("application_role_id")
  private String applicationRoleId;

  @JsonProperty("application")
  private ApplicationDto application;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("role_sap")
  private Boolean roleSap;
}
