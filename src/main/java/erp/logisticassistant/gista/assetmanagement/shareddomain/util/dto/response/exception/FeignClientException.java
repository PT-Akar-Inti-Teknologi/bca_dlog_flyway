package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@AllArgsConstructor
public class FeignClientException extends RuntimeException implements Serializable {

  final String urlRequest;

  final Integer statusCode;

  final transient GlobalResponse response;
}
