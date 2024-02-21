package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositeRoleDto extends AuditableCacheDto{
    @JsonProperty("composite_role_id")
    private String compositeRoleId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("application_role")
    private List<ApplicationRoleDto> applicationRole;

}