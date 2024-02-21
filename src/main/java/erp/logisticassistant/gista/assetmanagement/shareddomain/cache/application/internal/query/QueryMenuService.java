package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Menu;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class QueryMenuService {

    private final MenuRepository menuRepository;

    public Optional<Menu> findMenuById(String menuId) {
        return menuRepository.findByMenuId(menuId);
    }
}