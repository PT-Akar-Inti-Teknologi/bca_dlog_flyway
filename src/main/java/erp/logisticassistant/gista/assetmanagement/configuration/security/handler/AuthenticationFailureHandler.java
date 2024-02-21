package erp.logisticassistant.gista.assetmanagement.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.FORBIDDEN;
import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.UNAUTHORIZED;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler extends KeycloakAuthenticationFailureHandler {

    private final ConvertTransform convertTransform;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException authException) throws IOException, ServletException {
        log.info("Auth Failure Exception Handler", authException);
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Type", "application/json");
        response.setStatus(FORBIDDEN.getHttpStatus().value());
        ObjectMapper mapper = new ObjectMapper();
        String headerResponse = response.getHeader("WWW-Authenticate") ;

        mapper.writeValue(out, new GlobalResponse(
                convertTransform.getTimestamp(LocalDateTime.now()),
                UNAUTHORIZED.getHttpStatus().value(),
                UNAUTHORIZED.getCodeDesc(),
                UNAUTHORIZED,
                headerResponse.contains("Invalid token signature") ? "Invalid token signature" : "Token is expired",
                null));
        out.flush();
    }
}