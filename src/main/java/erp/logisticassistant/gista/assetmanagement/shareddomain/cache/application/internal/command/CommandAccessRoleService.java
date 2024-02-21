package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundMenuService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.AccessRoleRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.AccessRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.AccessRoleTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandAccessRoleService {
    private final InboundApplicationService inboundCacheApplicationService;
    private final InboundApplicationRoleService inboundCacheApplicationRoleService;
    private final InboundMenuService inboundMenuService;
    private final AccessRoleTransform accessRoleTransform;
    private final AccessRoleRepository accessRoleRepository;
    private final ApplicationProperties applicationProperties;

    public Boolean checkProcessingData(AccessRoleDto accessRoleDto){
        AtomicBoolean processingData = new AtomicBoolean(false);

        if(accessRoleDto.getApplicationRole().getApplication() != null && accessRoleDto.getApplicationRole().getApplication().getApplicationId()
                    .equals(applicationProperties.getSpring().getApplication().getId())){
                processingData.set(true);
        }
        return processingData.get();
    }

    public void processCreateAccessRole(AccessRoleDto dtoCreate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoCreate.getApplicationRole().getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoCreate.getApplicationRole().getApplication());

        if (!inboundMenuService.checkMenuById(dtoCreate.getMenu().getMenuId()))
            inboundMenuService.processCreateMenu(dtoCreate.getMenu());

        if (!inboundCacheApplicationRoleService.checkApplicationRoleById(dtoCreate.getApplicationRole().getApplicationRoleId()))
            inboundCacheApplicationRoleService.processCreateApplicationRole(dtoCreate.getApplicationRole());

        accessRoleRepository.save(accessRoleTransform.toCreateCacheAccessRole(dtoCreate));
    }

    @Transactional
    public void processUpdateAccessRole(AccessRoleDto dtoUpdate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoUpdate.getApplicationRole().getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoUpdate.getApplicationRole().getApplication());

        if (!inboundMenuService.checkMenuById(dtoUpdate.getMenu().getMenuId()))
            inboundMenuService.processCreateMenu(dtoUpdate.getMenu());

        if (!inboundCacheApplicationRoleService.checkApplicationRoleById(dtoUpdate.getApplicationRole().getApplicationRoleId()))
            inboundCacheApplicationRoleService.processCreateApplicationRole(dtoUpdate.getApplicationRole());

        accessRoleRepository.save(accessRoleTransform.toCreateCacheAccessRole(dtoUpdate));
    }

    public void processSoftDeleteAccessRole(AccessRoleDto dtoDelete) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoDelete.getApplicationRole().getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoDelete.getApplicationRole().getApplication());

        if (!inboundMenuService.checkMenuById(dtoDelete.getMenu().getMenuId()))
            inboundMenuService.processCreateMenu(dtoDelete.getMenu());

        if (!inboundCacheApplicationRoleService.checkApplicationRoleById(dtoDelete.getApplicationRole().getApplicationRoleId()))
            inboundCacheApplicationRoleService.processCreateApplicationRole(dtoDelete.getApplicationRole());

        accessRoleRepository.save(accessRoleTransform.toCreateCacheAccessRole(dtoDelete));
    }
}