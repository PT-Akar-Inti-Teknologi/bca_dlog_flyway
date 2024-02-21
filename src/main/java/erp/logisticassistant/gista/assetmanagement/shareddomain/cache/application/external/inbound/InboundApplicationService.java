package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query.QueryApplicationService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InboundApplicationService {
    
    private final QueryApplicationService queryCacheApplicationService;
    private final CommandApplicationService commandCacheApplicationService;

    public boolean checkApplicationById(String applicationId) {
        return queryCacheApplicationService.findApplicationById(applicationId).isPresent();
    }

    public void processCreateApplication(ApplicationDto dto) {
        commandCacheApplicationService.processCreateApplication(dto);
    }
}