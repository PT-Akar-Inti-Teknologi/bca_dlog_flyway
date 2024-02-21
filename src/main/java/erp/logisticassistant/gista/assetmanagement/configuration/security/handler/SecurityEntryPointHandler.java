package erp.logisticassistant.gista.assetmanagement.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.UNAUTHORIZED;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityEntryPointHandler implements AuthenticationEntryPoint {

  private final ConvertTransform convertTransform;

  @Override
  public void commence(HttpServletRequest request,
                      HttpServletResponse response,
                      AuthenticationException authException) throws IOException {
    log.error("Auth Entry point Exception Handler", authException);
    response.setHeader("Content-Type", "application/json");
    response.setStatus(UNAUTHORIZED.getHttpStatus().value());
    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, new GlobalResponse(
            convertTransform.getTimestamp(LocalDateTime.now()),
            UNAUTHORIZED.getHttpStatus().value(),
            UNAUTHORIZED.getCodeDesc(),
            UNAUTHORIZED,
            UNAUTHORIZED.getMessage(),
            null));
    out.flush();
  }
}
