package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestAttachmentSubmission {
    private String nomorRequest;
    private MultipartFile file;
    private String documentType;
    private String description;
    private String transactionType;
    private String transactionIde;
    private String checksum;
    private Boolean deletionFlag;
}
