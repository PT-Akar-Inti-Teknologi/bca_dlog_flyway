package erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.AuditableCacheDto;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAttachmentClient extends AuditableCacheDto {
    private String documentNo;
    private ResponseAttachmentDocumentType documentType;
    private String description;
    private String fileUrl;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String originFileName;
    private String directory;
    private String checksum;
    private Boolean local;
    private String fileType;
    private ResponseAttachmentDocumentDetailResponse detail;
}
