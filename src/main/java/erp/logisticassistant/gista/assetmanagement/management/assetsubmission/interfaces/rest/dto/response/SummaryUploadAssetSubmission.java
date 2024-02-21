package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SummaryUploadAssetSubmission<T> {
    @JsonProperty("total_error")
    private Integer totalError;

    @JsonProperty("total_error_asset_submission")
    private Integer totalErrorAssetubmission;


    @JsonProperty("content")
    private T content;
}
