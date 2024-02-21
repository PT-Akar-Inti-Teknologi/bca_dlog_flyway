package erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StatusCode {
  CONNECTED(HttpStatus.OK, "GISTA-200", "connection"),
  DISCONNECTED(HttpStatus.INTERNAL_SERVER_ERROR, "GISTA-415", "keycloak disconnected"),
  OK(HttpStatus.OK, "GISTA-200", "success"),
  CREATED(HttpStatus.CREATED, "GISTA-201", "created"),
  CONFLICT(HttpStatus.CONFLICT, "GISTA-409", "conflict request"),
  NO_CONTENT(HttpStatus.NO_CONTENT, "GISTA-204", "no content request"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "GISTA-400", "argument is not valid"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "GISTA-401", "credentials is not valid"),
  FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "GISTA-403", "Anda tidak memiliki hak akses, harap ajukan penambahan role"),
  FORBIDDEN(HttpStatus.FORBIDDEN, "GISTA-403", "protected"),
  INVALID_JSON_PAYLOAD(HttpStatus.BAD_REQUEST, "GISTA-415", "JSON is not valid"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GISTA-500", "something wrong"),
  UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "GISTA-422", "Unprocessable Entity"),
  MAINTENANCE_MODE(HttpStatus.INTERNAL_SERVER_ERROR, "GISTA-503", "This service under maintenance mode, please contact administrator"),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "GISTA-405", "HTTP method is not valid"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "GISTA-404", "data not found");

  private final HttpStatus httpStatus;
  private final String codeDesc;
  private final String message;

}
