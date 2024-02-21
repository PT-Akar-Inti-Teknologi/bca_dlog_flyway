package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository
        extends JpaRepository<Menu, String>,
                RevisionRepository<Menu, String, Long>,
                JpaSpecificationExecutor<Menu> {

    Optional<Menu> findByMenuId(String id);

    Optional<Menu> findByMenuIdAndEnabledTrue(String id);

    Optional<Menu> findByMenuIdAndCreateTrue(String menuId);

    Optional<Menu> findByMenuIdAndReadTrue(String menuId);

    Optional<Menu> findByMenuIdAndUpdateTrue(String menuId);

    Optional<Menu> findByMenuIdAndDeleteTrue(String menuId);

    List<Menu> findByEndpointIsNotNull();

    List<Menu> findByMenuIdInAndCreateTrueAndEnabledTrue(List<String> menuIds);

    List<Menu> findByMenuIdInAndUpdateTrueAndEnabledTrue(List<String> menuIds);

    List<Menu> findByMenuIdInAndReadTrueAndEnabledTrue(List<String> menuIds);

    List<Menu> findByMenuIdInAndDeleteTrueAndEnabledTrue(List<String> menuIds);
}
