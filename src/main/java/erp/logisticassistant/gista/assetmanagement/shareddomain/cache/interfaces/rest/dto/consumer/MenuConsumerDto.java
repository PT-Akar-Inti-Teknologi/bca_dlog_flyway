package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.MenuDto;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuConsumerDto {
    @JsonProperty("action")
    private String action;
    @JsonProperty("data")
    private MenuDto data;
}
