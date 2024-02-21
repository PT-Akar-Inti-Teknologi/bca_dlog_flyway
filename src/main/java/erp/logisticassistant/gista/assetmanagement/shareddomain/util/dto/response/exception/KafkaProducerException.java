package erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@AllArgsConstructor
public class KafkaProducerException extends RuntimeException implements Serializable {

  final String topic;

  final String message;

}
