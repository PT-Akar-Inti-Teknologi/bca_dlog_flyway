package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestAssetSubmissionFilter {
    private String search;

    private String status;
}
