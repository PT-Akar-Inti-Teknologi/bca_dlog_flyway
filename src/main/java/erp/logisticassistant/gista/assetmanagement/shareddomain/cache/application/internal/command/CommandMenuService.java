package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.MenuRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.MenuDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.MenuTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@AllArgsConstructor
public class CommandMenuService {

    private final InboundApplicationService inboundCacheApplicationService;
    private final MenuTransform cacheMenuTransform;
    private final MenuRepository cacheMenuRepository;
    private final ApplicationProperties applicationProperties;

    public Boolean checkProcessingData(MenuDto dto){
        AtomicBoolean processingData = new AtomicBoolean(false);

        if(dto.getApplication() != null && dto.getApplication().getApplicationId().equals(applicationProperties.getSpring().getApplication().getId()))
                processingData.set(true);

        return processingData.get();
    }

    public void processCreateMenu(MenuDto dtoCreate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoCreate.getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoCreate.getApplication());

        cacheMenuRepository.save(cacheMenuTransform.toCreateCacheMenu(dtoCreate));
    }

    public void processUpdateMenu(MenuDto dtoUpdate) {
        if (!inboundCacheApplicationService.checkApplicationById(dtoUpdate.getApplication().getApplicationId()))
            inboundCacheApplicationService.processCreateApplication(dtoUpdate.getApplication());

        cacheMenuRepository.save(cacheMenuTransform.toCreateCacheMenu(dtoUpdate));
    }
}