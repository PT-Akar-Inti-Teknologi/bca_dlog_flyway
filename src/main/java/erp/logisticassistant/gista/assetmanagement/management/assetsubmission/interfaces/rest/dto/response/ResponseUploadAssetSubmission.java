package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUploadDetail;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUploadAssetSubmission {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("asset_submission_detil")
    private List<RequestAssetSubmissionUploadDetail> assetSubmissionDetails;

    @JsonProperty("action")
    private String action;

    @JsonProperty("error")
    private Boolean error;

    @JsonProperty("success")
    private Boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;
}
