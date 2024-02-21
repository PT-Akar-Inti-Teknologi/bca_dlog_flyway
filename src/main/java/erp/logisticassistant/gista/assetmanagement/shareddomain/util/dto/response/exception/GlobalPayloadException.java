package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalPayloadException extends Exception{
    @JsonProperty("field")
    private final String payload;
    @JsonProperty("message")
    private final String message;

}
