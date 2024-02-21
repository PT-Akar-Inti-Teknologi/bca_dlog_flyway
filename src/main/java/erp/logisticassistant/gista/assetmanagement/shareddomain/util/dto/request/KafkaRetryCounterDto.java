package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KafkaRetryCounterDto {
    
    @JsonProperty("retry_count")
    private int retryCount;
}
