package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDto extends AuditableCacheDto{
  @JsonProperty("application_id")
  private String applicationId;

  @JsonProperty("name")
  private String name;

  @JsonProperty(value = "description")
  private String description;

  @JsonProperty("access_desktop")
  private Boolean desktop;

  @JsonProperty("access_mobile")
  private Boolean mobile;

  @JsonProperty("type_internet")
  private Boolean typeInternet;
}
