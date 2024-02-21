package erp.logisticassistant.gista.assetmanagement.configuration.security;

import brave.Tracer;
import erp.logisticassistant.gista.assetmanagement.configuration.security.handler.SecurityExpressionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MDCFilter implements Filter{

  private final Tracer tracer;
  private final SecurityExpressionHandler expressionHandler;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String parameterUsername = "username";
    String parameterTraceId = "X-B3-TraceId";
    String parameterSpanId = "X-B3-SpanId";
    String parameterParentId = "X-B3-ParentId";

    request.setAttribute(parameterTraceId, tracer.currentSpan().context().traceIdString());
    request.setAttribute(parameterSpanId, tracer.currentSpan().context().spanIdString());
    request.setAttribute(parameterParentId, tracer.currentSpan().context().parentIdString());

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.setHeader(parameterTraceId, tracer.currentSpan().context().traceIdString());
    httpResponse.setHeader(parameterSpanId, tracer.currentSpan().context().spanIdString());
    httpResponse.setHeader(parameterParentId, tracer.currentSpan().context().parentIdString());

    MDC.put(parameterTraceId, tracer.currentSpan().context().traceIdString());
    MDC.put(parameterSpanId, tracer.currentSpan().context().spanIdString());

    Authentication authentication = expressionHandler.getAuthentication(SecurityContextHolder.getContext());
    if (authentication instanceof KeycloakAuthenticationToken) {
      KeycloakPrincipal principal = expressionHandler.getPrincipal(authentication);
      KeycloakSecurityContext session = expressionHandler.getSecurityContext(principal);
      AccessToken token = session.getToken();
      MDC.put(parameterUsername, token.getPreferredUsername());
      request.setAttribute(parameterUsername, token.getName());
    }
    try{
      filterChain.doFilter(request, response);
    }finally {
      if(MDC.get(parameterUsername) != null){
        MDC.remove(parameterUsername);
      }
    }
  }
}