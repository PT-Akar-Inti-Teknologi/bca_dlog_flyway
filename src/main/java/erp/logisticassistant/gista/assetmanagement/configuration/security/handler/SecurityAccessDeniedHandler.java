package erp.logisticassistant.gista.assetmanagement.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.FORBIDDEN_ACCESS;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

  private final ConvertTransform convertTransform;

  @Override
  public void handle(HttpServletRequest request,
                     HttpServletResponse response,
                     AccessDeniedException authException) throws IOException, ServletException {
    log.error("Auth Access Denied Exception Handler", authException);
    response.setHeader("Content-Type", "application/json");
    response.setStatus(FORBIDDEN_ACCESS.getHttpStatus().value());
    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, new GlobalResponse(
            convertTransform.getTimestamp(LocalDateTime.now()),
            FORBIDDEN_ACCESS.getHttpStatus().value(),
            FORBIDDEN_ACCESS.getCodeDesc(),
            FORBIDDEN_ACCESS,
            FORBIDDEN_ACCESS.getMessage(),
            null));
    out.flush();
  }
}
