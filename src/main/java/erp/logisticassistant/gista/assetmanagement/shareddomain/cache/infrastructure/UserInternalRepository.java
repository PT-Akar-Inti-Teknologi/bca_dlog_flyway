package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserInternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserInternalRepository
        extends JpaRepository<UserInternal, String>,
                JpaSpecificationExecutor<UserInternal> {

    Optional<UserInternal> findByUserUserId(String id);
}