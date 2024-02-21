package erp.logisticassistant.gista.assetmanagement.configuration.exception;

import com.google.common.base.CaseFormat;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.FeignClientException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalPayloadException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.*;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class HandlerGlobalExceptionConfiguration extends ResponseEntityExceptionHandler {

  private final ApplicationProperties applicationProperties;
  private final ConvertTransform convertTransform;

  @ExceptionHandler(GlobalException.class)
  protected ResponseEntity<Object> globalCustomExceptionHandler(
          GlobalException ex,
          WebRequest request) {
    log.warn(GlobalConstant.FORMAT_LOG, ex);
    GlobalResponse response = new GlobalResponse(
            convertTransform.getTimestamp(LocalDateTime.now()),
            ex.getStatusCode().getHttpStatus().value(),
            ex.getStatusCode().getCodeDesc(),
            ex.getStatusCode(),
            ex.getMessage(),
            null);
    return new ResponseEntity<>(response, new HttpHeaders(), ex.getStatusCode().getHttpStatus());
  }

  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ResponseEntity<Object> handle(
          HttpServletRequest req,
          Exception ex) {
    log.error(GlobalConstant.FORMAT_LOG, ex);
    GlobalResponse response = new GlobalResponse(
            convertTransform.getTimestamp(LocalDateTime.now()),
            INTERNAL_SERVER_ERROR.getHttpStatus().value(),
            INTERNAL_SERVER_ERROR.getCodeDesc(),
            INTERNAL_SERVER_ERROR,
            GlobalMessage.Core.ERROR_INTERNAL_SERVER.replaceAll(GlobalConstant.FORMAT_EMAIL_REPLACE, applicationProperties.getGista().getMail().getHelp()),
            null);
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(FeignClientException.class)
  protected ResponseEntity<Object> feignClientExceptionHandler(
          FeignClientException ex,
          WebRequest request) {
    log.warn(GlobalConstant.FORMAT_LOG, ex);
    GlobalResponse response = ex.getResponse();
    return new ResponseEntity<>(response, new HttpHeaders(), ex.getStatusCode());
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleViolation(
          HttpServletRequest req,
          Exception ex) {
    log.warn(GlobalConstant.FORMAT_LOG, ex);
    List<GlobalPayloadException> payloadExceptions = new ArrayList<>();
    String respMessage = null;
    String[] arrMessage = ex.getMessage().isBlank() ? null : ex.getMessage().split(", ");
    if(arrMessage == null){
      respMessage = GlobalMessage.Core.ERROR_INTERNAL_SERVER;
    } else {
      payloadExceptions.add(convertTransform.filterArrayMessage(arrMessage));
      respMessage = payloadExceptions.get(0).getMessage();
    }
    GlobalResponse<List<GlobalPayloadException>> response = new GlobalResponse<>(
            convertTransform.getTimestamp(LocalDateTime.now()),
            StatusCode.UNPROCESSABLE_ENTITY.getHttpStatus().value(),
            UNPROCESSABLE_ENTITY.getCodeDesc(),
            UNPROCESSABLE_ENTITY,
            respMessage,
            payloadExceptions);
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
          Exception ex,
          Object body,
          @NonNull HttpHeaders headers,
          @NonNull HttpStatus status,
          @NonNull WebRequest request) {
    GlobalResponse response;
    log.warn(GlobalConstant.FORMAT_LOG, ex);
    if (ex.getClass().equals(HttpRequestMethodNotSupportedException.class)) {
      response = new GlobalResponse(
              convertTransform.getTimestamp(LocalDateTime.now()),
              METHOD_NOT_ALLOWED.getHttpStatus().value(),
              METHOD_NOT_ALLOWED.getCodeDesc(),
              METHOD_NOT_ALLOWED,
              GlobalMessage.Core.ERROR_INTERNAL_SERVER.replaceAll(GlobalConstant.FORMAT_EMAIL_REPLACE, applicationProperties.getGista().getMail().getHelp()),
              null);
    }
    else if (ex.getClass().equals(HttpMediaTypeNotSupportedException.class)) {
      response = new GlobalResponse(
              convertTransform.getTimestamp(LocalDateTime.now()),
              status.value(),
              INVALID_JSON_PAYLOAD.getCodeDesc(),
              INVALID_JSON_PAYLOAD,
              GlobalMessage.Core.ERROR_INTERNAL_SERVER.replaceAll(GlobalConstant.FORMAT_EMAIL_REPLACE, applicationProperties.getGista().getMail().getHelp()),
              null);
    } else {
      response = new GlobalResponse(
              convertTransform.getTimestamp(LocalDateTime.now()),
              StatusCode.BAD_REQUEST.getHttpStatus().value(),
              BAD_REQUEST.getCodeDesc(),
              BAD_REQUEST,
              GlobalMessage.Core.ERROR_INTERNAL_SERVER.replaceAll(GlobalConstant.FORMAT_EMAIL_REPLACE, applicationProperties.getGista().getMail().getHelp()),
              null);
    }
    return new ResponseEntity<>(response, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          final HttpHeaders headers,
          final HttpStatus status,
          final WebRequest request) {
    log.warn(GlobalConstant.FORMAT_LOG, ex);
    List<GlobalPayloadException> payloadExceptions = new ArrayList<>();
    String message = null;
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      if(message == null){
        message = error.getDefaultMessage();
      }
      payloadExceptions.add(new GlobalPayloadException(
              CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,  error.getField()),
              error.getDefaultMessage()));
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      if(message == null){
        message = error.getDefaultMessage();
      }
      payloadExceptions.add(new GlobalPayloadException(error.getObjectName(), error.getDefaultMessage()));
    }
    GlobalResponse<List<GlobalPayloadException>> response = new GlobalResponse<>(
            convertTransform.getTimestamp(LocalDateTime.now()),
            StatusCode.UNPROCESSABLE_ENTITY.getHttpStatus().value(),
            UNPROCESSABLE_ENTITY.getCodeDesc(),
            UNPROCESSABLE_ENTITY,
            message,
            payloadExceptions);
    return new ResponseEntity<>(response, headers, StatusCode.UNPROCESSABLE_ENTITY.getHttpStatus());
  }
}
