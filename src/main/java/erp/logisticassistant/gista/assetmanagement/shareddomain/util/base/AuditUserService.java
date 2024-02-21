package erp.logisticassistant.gista.assetmanagement.shareddomain.util.base;

import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuditUserService {
    public String getUsernameSigned(ApplicationProperties applicationProperties){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken) {
            return ((KeycloakPrincipal) authentication.getPrincipal()).getKeycloakSecurityContext().getToken().getPreferredUsername();
        }else{
            return applicationProperties.getGista().getKeycloak().getManagement().getUsername();
        }
    }
}
