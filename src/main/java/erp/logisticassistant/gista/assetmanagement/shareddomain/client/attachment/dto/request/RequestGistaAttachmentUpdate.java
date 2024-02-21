package erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class RequestGistaAttachmentUpdate {
    private String transactionTypeId;
    private String transactionTypeOriginId;
    private String transactionId;
    private String documentNo;
    private String modifiedBy;
}
