package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestAssetSubmissionUpload {
    @Valid
    @NotNull(message = GlobalMessage.Annotation.ERROR_EMPTY_DETAIL_ASSET_SUBMISSION)
    @JsonProperty("asset_submission_details")
    private List<RequestAssetSubmissionUploadDetail> assetSubmissionDetails;

}
