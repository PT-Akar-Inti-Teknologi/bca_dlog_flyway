package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command.CommandMenuService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query.QueryMenuService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.MenuDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InboundMenuService {
    private final QueryMenuService queryMenuService;
    private final CommandMenuService commandMenuService;

    public boolean checkMenuById(String menuId) {
        return queryMenuService.findMenuById(menuId).isPresent();
    }

    public void processCreateMenu(MenuDto dto) {
        commandMenuService.processCreateMenu(dto);
    }
}