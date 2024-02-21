package erp.logisticassistant.gista.assetmanagement.shareddomain.service;

import erp.logisticassistant.gista.assetmanagement.configuration.security.handler.SecurityExpressionHandler;
import erp.logisticassistant.gista.assetmanagement.management.user.application.QueryBaseUserInternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserLoginService {
    private final SecurityExpressionHandler securityExpressionHandler;
    private final QueryBaseUserInternal queryBaseUserInternal;

    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken)
            return ((KeycloakPrincipal<?>) authentication.getPrincipal()).getKeycloakSecurityContext().getToken().getPreferredUsername();
        return "";
    }

    public String getToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken)
            return ((KeycloakPrincipal<?>) authentication.getPrincipal()).getKeycloakSecurityContext().getTokenString();
        return "";
    }
    public String getSubDepartmentCode(){
        return queryBaseUserInternal.getSubDepartmentCode(getUsername());
    }
}
