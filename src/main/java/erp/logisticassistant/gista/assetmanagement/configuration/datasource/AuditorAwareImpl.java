package erp.logisticassistant.gista.assetmanagement.configuration.datasource;

import erp.logisticassistant.gista.assetmanagement.configuration.security.handler.SecurityExpressionHandler;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final SecurityExpressionHandler expressionHandler;
    private final ApplicationProperties applicationProperties;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = expressionHandler.getAuthentication(SecurityContextHolder.getContext());
        if (authentication instanceof KeycloakAuthenticationToken) {
            KeycloakPrincipal principal = expressionHandler.getPrincipal(authentication);
            KeycloakSecurityContext session = expressionHandler.getSecurityContext(principal);
            AccessToken token = session.getToken();
            return Optional.of(token.getPreferredUsername());
        }else {
            return Optional.of(applicationProperties.getGista().getKeycloak().getManagement().getUsername());
        }
    }
}