package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import erp.logisticassistant.gista.assetmanagement.management.attachment.interfaces.rest.dto.ResponseAttachment;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAssetSubmissionHeader extends AuditableCacheDto{
    private String nomorRequest;
    private Integer jumlahAsset;
    private String note;
    private String requesterByName;
    private String requesterDt;
    private String requesterDepartmentName;
    private String processedByName;
    private String processedDt;
    private String processedDepartmentName;
    private Boolean isProcessed;
    private String status;
    List<ResponseAttachment> attachmentSubmissionList;
    private Boolean isApprover;
    private Boolean isRequester;
    private String userGroupName;
}
