package erp.logisticassistant.gista.assetmanagement.shareddomain.client.keycloak.application;

import erp.logisticassistant.gista.assetmanagement.shareddomain.client.keycloak.service.KeycloakClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakService {

  private final ApplicationProperties applicationProperties;

  private final KeycloakClient keycloakClient;

  public List<ResourceRepresentation> getPermission(String token, String owner, String scope) {
    return keycloakClient.requestPermission(
            GlobalConstant.TAG_AUTHENTICATION_TYPE + " " + token,
            applicationProperties.getKeycloak().getRealm(),
            owner,
            true,
            scope).getBody();
  }
}
