package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandApplicationRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query.QueryApplicationRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationRoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InboundApplicationRoleService {
    private final QueryApplicationRoleService queryCacheApplicationRoleService;
    private final CommandApplicationRoleService commandCacheApplicationRoleService;

    public boolean checkApplicationRoleById(String applicationRoleId) {
        return queryCacheApplicationRoleService.findApplicationRoleById(applicationRoleId).isPresent();
    }

    public void processCreateApplicationRole(ApplicationRoleDto dto) {
        commandCacheApplicationRoleService.processCreateApplicationRole(dto);
    }
}