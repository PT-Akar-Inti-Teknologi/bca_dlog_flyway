package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.outbound;

import erp.logisticassistant.gista.assetmanagement.configuration.security.handler.SecurityExpressionHandler;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboundUserService {
    private static final String ROLE_KEY_OPR = "isOpr";
    private static final String ROLE_PREFIX_OPR = "_OPR";
    private static final String ROLE_KEY_SPV = "isSpv";
    private static final String ROLE_PREFIX_SPV = "_SPV";
    private static final String ROLE_KEY_KANWIL = "isKanwil";
    private static final String ROLE_PREFIX_KANWIL = "_KANWIL";
    private static final String ROLE_KEY_VIEW= "isView";
    private static final String ROLE_PREFIX_VIEW = "_SUPPORT1,_SUPPORT2,_VIEW";
    private final SecurityExpressionHandler expressionHandler;
    private final ApplicationProperties applicationProperties;

    public List<String> getUserPermissions(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return expressionHandler.getUserPermission(auth);
    }
    public Boolean isOprBop(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> permissions = expressionHandler.getUserPermission(auth).stream()
                .filter( permissionsRoleName -> applicationProperties.getGista().getAsset().getRole().getAdmin().contains(permissionsRoleName) )
                .collect(Collectors.toList());
        AtomicBoolean isAdminOprBop = new AtomicBoolean(true);
        if (permissions.isEmpty() ) {
            isAdminOprBop.set(false);
        }
        return isAdminOprBop.get();
    }

    public boolean isOpr() {
        List<String> permissions = getUserPermissions();
        return containsRolePrefix(permissions, ROLE_PREFIX_OPR);
    }

    public boolean isSpv() {
        List<String> permissions = getUserPermissions();
        return containsRolePrefix(permissions, ROLE_PREFIX_SPV);
    }

    public boolean isKanwil() {
        List<String> permissions = getUserPermissions();
        return containsAnyRolePrefix(permissions, ROLE_PREFIX_KANWIL);
    }

    public boolean isView() {
        List<String> permissions = getUserPermissions();
        return containsAnyRolePrefix(permissions, ROLE_PREFIX_VIEW);
    }

    public Map<String, Boolean> isRole(){
        Map<String, Boolean> roleInfo = new HashMap<>();
        roleInfo.put(ROLE_KEY_OPR, isOpr());
        roleInfo.put(ROLE_KEY_SPV, isSpv());
        roleInfo.put(ROLE_KEY_KANWIL, isKanwil());
        roleInfo.put(ROLE_KEY_VIEW, isView());
        return roleInfo;
    }

    public boolean hasRole(String roleKey) {
        Map<String, Boolean> roleInfo = isRole();
        return roleInfo.containsKey(roleKey) && roleInfo.get(roleKey);
    }

    private boolean containsRolePrefix(List<String> roles, String prefix) {
        return roles.stream().anyMatch(role -> role.contains(prefix));
    }

    private boolean containsAnyRolePrefix(List<String> roles, String prefixes) {
        String[] prefixArray = prefixes.split("\\s*,\\s*");
        return roles.stream().anyMatch(role -> Arrays.stream(prefixArray).anyMatch(role::contains));
    }

    public List<String> getByApplicationRoleByGroup() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (Boolean.TRUE.equals(isView())) {
            return expressionHandler.getUserPermission(auth).stream()
                    .filter(permissionRoleName ->
                            applicationProperties.getGista().getAsset().getRole().getSupport().contains(permissionRoleName) ||
                            applicationProperties.getGista().getAsset().getRole().getAudit().contains(permissionRoleName))
                    .collect(Collectors.toList());
        } else {
            return getListPermissionsRoleAuth();
        }
    }

    public List<String> getListPermissionsRoleAuth(){
        List<String> asListRoles = new ArrayList<>();
        getUserPermissions().forEach(permissionRoleName ->{
            if (Boolean.FALSE.equals(this.isView())) {
                if (applicationProperties.getGista().getAsset().getRole().getTxb().contains(permissionRoleName)) {
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getTxb());
                } else if (applicationProperties.getGista().getAsset().getRole().getKanwil().contains(permissionRoleName)) {
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getKanwil());
                } else if (applicationProperties.getGista().getAsset().getRole().getAdmin().contains(permissionRoleName)){
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getAdmin());
                }
            }
        });
        return asListRoles;

    }

    public List<String> getListPermissionRoles(){
        List<String> asListRoles = new ArrayList<>();
        getUserPermissions().forEach( permissionRoleName ->{
            if (Boolean.FALSE.equals(this.isOprBop())){
                if (applicationProperties.getGista().getAsset().getRole().getKp().contains(permissionRoleName)){
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getKp());
                } else if (applicationProperties.getGista().getAsset().getRole().getTxb().contains(permissionRoleName)) {
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getTxb());
                } else if (applicationProperties.getGista().getAsset().getRole().getKanwil().contains(permissionRoleName)) {
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getKanwil());
                } else if (applicationProperties.getGista().getAsset().getRole().getSupport().contains(permissionRoleName)) {
                    setAddToListRoles(asListRoles, applicationProperties.getGista().getAsset().getRole().getSupport());
                }
            }
        });
        return asListRoles;

    }

    public void setAddToListRoles(List<String> asListRoles, List<String> assetPermissionRoles) {
        assetPermissionRoles
                .forEach(rolesName ->{
                    if (!rolesName.isEmpty()){
                        asListRoles.add(rolesName);
                    }
                });
    }

}
