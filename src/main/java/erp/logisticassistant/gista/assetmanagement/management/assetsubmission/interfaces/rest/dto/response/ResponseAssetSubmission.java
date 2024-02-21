package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAssetSubmission extends AuditableCacheDto{
    private String nomorRequest;
    private Integer jumlahAsset;
    private String note;
    private String requesterByUserName;
    private String requesterByName;
    private String requesterDt;
    private Boolean isProcessed;
    private String status;
    private String userGroupCreatedBy;
}
