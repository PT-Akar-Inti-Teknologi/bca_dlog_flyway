package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationRoleDto;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationRoleConsumerDto {
    @JsonProperty("action")
    private String action;

    @JsonProperty("data")
    private ApplicationRoleDto data;
}
