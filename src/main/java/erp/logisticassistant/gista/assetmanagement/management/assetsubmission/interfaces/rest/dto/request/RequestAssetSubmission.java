package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestAssetSubmission {
    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_DOCUMENT_NO)
    @JsonProperty("document_no")
    private String documentNo;

    @JsonProperty("document_type_id")
    private String documentTypeId;

    @JsonProperty("description")
    private String description;
}
