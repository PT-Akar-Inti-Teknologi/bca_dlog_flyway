package erp.logisticassistant.gista.assetmanagement.management.user.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.user.domain.entities.BaseUserInternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserInternalRepository extends JpaRepository<BaseUserInternal , String>,
        JpaSpecificationExecutor<BaseUserInternal> {
    Optional<BaseUserInternal> findByUserUsernameIgnoreCase(String username);
}
