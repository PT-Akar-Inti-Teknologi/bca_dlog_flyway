package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseDownloadAssetSubmission {
    private String nomorRequest;
    private Integer jumlahAsset;
    private String note;
    private String requesterByUserName;
    private String requesterByName;
    private String requesterDt;
    private String isProcessed;
    private String status;
}
