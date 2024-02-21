package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundCompositeRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.AssosiateRoleRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.AssosiateRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.AssosiateRoleTransform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CommandAssosiateRoleService {
    private final InboundApplicationService inboundCacheApplicationService;
    private final InboundApplicationRoleService inboundCacheApplicationRoleService;
    private final InboundCompositeRoleService inboundCacheCompositeRoleService;
    private final AssosiateRoleTransform assosiateRoleTransform;
    private final AssosiateRoleRepository assosiateRoleRepository;

    public void processCreateAssosiateRole(AssosiateRoleDto dto) {
        if (!inboundCacheApplicationService.checkApplicationById(dto.getApplicationRole().getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dto.getApplicationRole().getApplication());

        if (!inboundCacheApplicationRoleService.checkApplicationRoleById(dto.getApplicationRole().getApplicationRoleId()))
            inboundCacheApplicationRoleService.processCreateApplicationRole(dto.getApplicationRole());

        if (!inboundCacheCompositeRoleService.checkCompositeRoleById(dto.getCompositeRole().getCompositeRoleId()))
            inboundCacheCompositeRoleService.processCreateCompositeRole(dto.getCompositeRole());

        assosiateRoleRepository.save(assosiateRoleTransform.toCreateCacheAssosiateRole(dto));
    }

    public void processDeleteAssosiateRole(AssosiateRoleDto dto) {
        assosiateRoleRepository.delete(
                assosiateRoleRepository.findByKeyApplicationRoleIdAndKeyCompositeRoleId(
                        dto.getApplicationRole().getApplicationRoleId(), dto.getCompositeRole().getCompositeRoleId()));
    }
}