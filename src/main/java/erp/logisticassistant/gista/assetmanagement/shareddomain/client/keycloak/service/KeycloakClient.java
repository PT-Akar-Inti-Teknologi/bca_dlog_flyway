package erp.logisticassistant.gista.assetmanagement.shareddomain.client.keycloak.service;

import erp.logisticassistant.gista.assetmanagement.configuration.feign.FeignErrorDecoder;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "Keycloak", url = "${keycloak.auth-server-url}", configuration = FeignErrorDecoder.class)
public interface KeycloakClient {
    @GetMapping(value = "/realms/{realm}/authz/protection/resource_set",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ResourceRepresentation>> requestPermission(
            @RequestHeader("Authorization") String token,
            @RequestParam("realm") String realm,
            @RequestParam("owner") String owner,
            @RequestParam("deep") Boolean deep,
            @RequestParam("scope") String scope);
}
