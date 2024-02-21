package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SummaryUploadResponse<T>{
  @JsonProperty("total_error")
  private Integer totalError;

  @JsonProperty("content")
  private List<T> content;

}
