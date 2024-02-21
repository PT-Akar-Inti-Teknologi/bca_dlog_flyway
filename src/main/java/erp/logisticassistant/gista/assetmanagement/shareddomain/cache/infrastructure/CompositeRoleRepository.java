package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.CompositeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CompositeRoleRepository
        extends JpaRepository<CompositeRole, String>,
                JpaSpecificationExecutor<CompositeRole> {
    Optional<CompositeRole> findByCompositeRoleId(String compositeRoleId);
}