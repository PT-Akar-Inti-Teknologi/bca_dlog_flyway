package erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseTransactionType {
    private String transactionTypeId;
    private String name;
    private String directory;
}
