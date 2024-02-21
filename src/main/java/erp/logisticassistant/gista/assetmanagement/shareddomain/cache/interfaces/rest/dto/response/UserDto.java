package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AuditableCacheDto{
  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("user_id")
  private String userId;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("username")
  private String username;

  @JsonProperty("name")
  private String name;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("email")
  private String email;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("internal")
  private Boolean internal;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("detil")
  private UserDetailDto userDetil;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("roles")
  private List<UserRoleDto> roles;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("external")
  private Boolean external;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("enabled")
  private Boolean enabled;

}
