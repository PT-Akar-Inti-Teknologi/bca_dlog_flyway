package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandCompositeRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query.QueryCompositeRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.CompositeRoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InboundCompositeRoleService {
    private final QueryCompositeRoleService queryCacheCompositeRoleService;
    private final CommandCompositeRoleService commandCacheCompositeRoleService;

    public boolean checkCompositeRoleById(String compositeRoleId) {
        return queryCacheCompositeRoleService.findCompositeRoleById(compositeRoleId).isPresent();
    }

    public void processCreateCompositeRole(CompositeRoleDto dto) {
        commandCacheCompositeRoleService.processCreateCompositeRole(dto);
    }
}