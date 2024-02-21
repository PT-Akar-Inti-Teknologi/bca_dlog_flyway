package erp.logisticassistant.gista.assetmanagement.management.attachment.interfaces.rest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.AuditableCacheDto;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAttachment extends AuditableCacheDto {
    private String documentNo;
    private String documentType;
    private String transactionType;
    private String transactionId;
    private String fileName;
    private String fileType;
    private String description;
}
