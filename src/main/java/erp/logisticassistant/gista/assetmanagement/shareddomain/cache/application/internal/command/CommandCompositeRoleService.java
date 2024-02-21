package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.CompositeRoleRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.CompositeRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.CompositeRoleTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandCompositeRoleService {
    private final CompositeRoleTransform compositeRoleTransform;
    private final CompositeRoleRepository compositeRoleRepository;

    public void processCreateCompositeRole(CompositeRoleDto dto) {
        compositeRoleRepository.save(compositeRoleTransform.toCreateCacheCompositeRole(dto));
    }

    public void processUpdateCompositeRole(CompositeRoleDto dto) {
        compositeRoleRepository.save(compositeRoleTransform.toCreateCacheCompositeRole(dto));
    }

    public void processDeleteCompositeRole(CompositeRoleDto dto) {
        compositeRoleRepository.save(compositeRoleTransform.toCreateCacheCompositeRole(dto));
    }
}