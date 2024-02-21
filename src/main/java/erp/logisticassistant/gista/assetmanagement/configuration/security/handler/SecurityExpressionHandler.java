package erp.logisticassistant.gista.assetmanagement.configuration.security.handler;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AccessRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Menu;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.service.MenuSpecification;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.AccessRoleRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.MenuRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.keycloak.application.KeycloakService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityExpressionHandler{

    private final ApplicationProperties applicationProperties;
    private final AccessRoleRepository accessRoleRepository;
    private final MenuRepository menuRepository;
    private final MenuSpecification menuSpecification;

    private final KeycloakService keycloakService;

    public Authentication getAuthentication(SecurityContext context){
        return context.getAuthentication();
    }

    public KeycloakPrincipal getPrincipal(Authentication authentication){
        return (KeycloakPrincipal) authentication.getPrincipal();
    }

    public KeycloakSecurityContext getSecurityContext(KeycloakPrincipal principal) {
        return principal.getKeycloakSecurityContext();
    }

    public List<String> getUserPermission(Authentication authentication) {
        ArrayList<String> permission = new ArrayList<>();
        KeycloakPrincipal userPrinsipal = getPrincipal(authentication);
        AccessToken userAccessToken = userPrinsipal.getKeycloakSecurityContext().getToken();
        Map<String, AccessToken.Access> applicationRoleUser = userAccessToken.getResourceAccess();
        applicationRoleUser.values().forEach( cliens -> permission.addAll(cliens.getRoles()) );

        return permission;
    }

    public List<String> getTicketPermission(KeycloakSecurityContext authentication, HttpServletRequest request, Optional<Map.Entry<String, List<String>>> clientIntegration){
        List<String> menuPermissions = new ArrayList<>();
        authentication.getToken().getAuthorization().getPermissions().forEach( permission -> {
            List<ResourceRepresentation> permissionResources = keycloakService.getPermission(authentication.getTokenString(), clientIntegration.get().getKey(), request.getMethod());
            if(!permissionResources.isEmpty() && clientIntegration.isPresent() && clientIntegration.get().getValue() != null){
                clientIntegration.get().getValue().forEach(module ->
                        permissionResources.forEach(permissionResource -> {
                            if (permissionResource.getType().equals(module)) {
                                menuPermissions.addAll(permissionResource.getUris());
                            }
                        })
                );

            }
        });
        return menuPermissions.stream().distinct().collect(Collectors.toList());
    }

    public List<String> filterPathPerSlash(String path){
        List<String> pathSearch = new ArrayList<>();
        List<String> pathPerSlash = Arrays.asList(path.split("/"));
        for(int x = pathPerSlash.size() - 1 ; x >= 0; x -= 1){
            if(!pathPerSlash.get(x).equals("")) {
                String tmpPath = "/".concat(pathPerSlash.get(x));
                for (int y = x - 1; y >= 0; y -= 1) {
                    if(!pathPerSlash.get(y).equals("")) {
                        tmpPath = "/".concat(pathPerSlash.get(y).concat(tmpPath));
                    }
                }
                pathSearch.add(tmpPath);
            }
        }
        return pathSearch;
    }

    public boolean checkAccesibleContents(Authentication authentication, HttpServletRequest request) {
        if(authentication instanceof KeycloakAuthenticationToken) {
            // Check SA Loged
            if(applicationProperties
                    .getGista().getKeycloak().getManagement().getUsername()
                    .equals(getPrincipal(authentication).getKeycloakSecurityContext().getToken().getPreferredUsername())){
                return true;
            }
            // Check Account Loged
            if (request.getMethod() != null && request.getServletPath() != null) {
                return checkAccountLoged(authentication, request);
            }
            return false;
        }else{
            return false;
        }
    }

    private boolean checkAccountLoged(Authentication authentication, HttpServletRequest request) {
        Optional<Map.Entry<String, List<String>>> clientIntegration = checkClientIntegration(getPrincipal(authentication)
                .getKeycloakSecurityContext().getToken().getIssuedFor());
        if(clientIntegration.isPresent()){
            List<String> ticketMenu = getTicketPermission(getPrincipal(authentication).getKeycloakSecurityContext(), request, clientIntegration);
            List<String> contentMenu = checkMenuActived(request.getMethod(), request.getServletPath(), isInternet(request), ticketMenu);
            return !contentMenu.isEmpty();
        }else{
            List<String> contentMenu = checkMenuActived(request.getMethod(), request.getServletPath(), isInternet(request), null);
            if (contentMenu.isEmpty())
                return false;

            return checkUserAccessContent(request.getMethod(), (ArrayList<String>) getUserPermission(authentication), contentMenu);
        }
    }

    private Optional<Map.Entry<String, List<String>>> checkClientIntegration(String clientIntegration) {
        if(applicationProperties.getGista().getKeycloak().getManagement().getClient().getIntegrations() == null)
            return Optional.empty();

        return applicationProperties.getGista().getKeycloak().getManagement().getClient().getIntegrations().entrySet().stream().filter( data ->
                data.getKey().equals(clientIntegration)
        ).collect(Collectors.toList()).stream().findFirst();
    }

    public Boolean checkUserAccessContent(String method, ArrayList<String> roles, List<String> menuActiveList) {
        List<AccessRole> accessRoles = new ArrayList<>();
        switch (method) {
            case "POST":
                accessRoles = accessRoleRepository.findAllByMenuMenuIdInAndApplicationRoleNameInAndCreateTrueAndEnabledTrue(menuActiveList, roles);
                break;
            case "GET":
                accessRoles = accessRoleRepository.findAllByMenuMenuIdInAndApplicationRoleNameInAndReadTrueAndEnabledTrue(menuActiveList, roles);
                break;
            case "PUT":
                accessRoles = accessRoleRepository.findAllByMenuMenuIdInAndApplicationRoleNameInAndUpdateTrueAndEnabledTrue(menuActiveList, roles);
                break;
            case "DELETE":
                accessRoles = accessRoleRepository.findAllByMenuMenuIdInAndApplicationRoleNameInAndDeleteTrueAndEnabledTrue(menuActiveList, roles);
                break;
            default:
                break;
        }
        log.info("has total access : {}", accessRoles.size());
        return !accessRoles.isEmpty();
    }

    private Boolean isInternet(HttpServletRequest request){

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        log.info("client ipAddress : " + ipAddress);
        String finalIpAddress = ipAddress;
        Optional<String> ipIntranetMatch = applicationProperties.getSpring().getSecurity().getIntranetIpPrefix().stream().filter(d ->
                finalIpAddress.startsWith(d)
        ).findAny();

        return !ipIntranetMatch.isPresent();
    }

    public List<String> checkMenuActived(String method, String path, Boolean isInternet, List<String> ticketMenu) {
        List<String> filterPath = filterPathPerSlash(path);

        List<String> menuIdList = new ArrayList<>();

        for(String pathSearch : filterPath){
            List<Menu> menuActivedList = menuRepository.findAll(menuSpecification.contentFilter(pathSearch, method, false, isInternet, ticketMenu));
            if(!menuActivedList.isEmpty()){
                menuActivedList.forEach( menu -> {
                    if(new AntPathMatcher().match(menu.getEndpoint(), path)) {
                        menuIdList.add(menu.getMenuId());
                    }
                });
                break;
            }
        }
        log.info("has total menu : {}", menuIdList.size());
        return menuIdList;
    }
}
