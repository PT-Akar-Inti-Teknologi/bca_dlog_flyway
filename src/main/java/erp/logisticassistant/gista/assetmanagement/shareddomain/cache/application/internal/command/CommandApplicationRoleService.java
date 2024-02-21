package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.ApplicationRoleRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.ApplicationRoleTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandApplicationRoleService {
    private final InboundApplicationService inboundCacheApplicationService;
    private final ApplicationRoleTransform applicationRoleTransform;
    private final ApplicationRoleRepository applicationRoleRepository;

    public void processCreateApplicationRole(ApplicationRoleDto dtoCreate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoCreate.getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoCreate.getApplication());

        applicationRoleRepository.save(applicationRoleTransform.toCreateCacheApplicationRole(dtoCreate));
    }

    public void processUpdateApplicationRole(ApplicationRoleDto dtoUpdate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoUpdate.getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoUpdate.getApplication());

        applicationRoleRepository.save(applicationRoleTransform.toCreateCacheApplicationRole(dtoUpdate));
    }

    public void processDeleteApplicationRole(ApplicationRoleDto dtoDelete) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoDelete.getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoDelete.getApplication());

        applicationRoleRepository.save(applicationRoleTransform.toCreateCacheApplicationRole(dtoDelete));
    }
}