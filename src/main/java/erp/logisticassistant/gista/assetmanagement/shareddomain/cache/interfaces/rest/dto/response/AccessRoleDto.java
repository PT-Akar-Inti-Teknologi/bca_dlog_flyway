package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessRoleDto extends AuditableCacheDto{

  @JsonProperty("access_id")
  private String accessId;

  @JsonProperty("menu")
  private MenuDto menu;

  @JsonProperty("application_role")
  private ApplicationRoleDto applicationRole;

  @JsonProperty("create")
  private Boolean create;

  @JsonProperty("read")
  private Boolean read;

  @JsonProperty("update")
  private Boolean update;

  @JsonProperty("delete")
  private Boolean delete;

  @JsonProperty("version")
  private Integer version;

}
