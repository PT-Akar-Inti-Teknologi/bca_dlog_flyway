package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUploadDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseAssetSubmissionUpload {
    @Valid
    @JsonProperty("asset_submission_details")
    private List<ResponseAssetSubmissionUploadDetail> assetSubmissionDetails;
}
