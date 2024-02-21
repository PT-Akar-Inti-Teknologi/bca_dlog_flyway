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
public class KafkaConsumerException extends RuntimeException implements Serializable {

  final String className;

  final String message;

  final int retryCount;

  final Boolean technicalFailure;

  final String payload;

  final String topic;

  final String key;
}
