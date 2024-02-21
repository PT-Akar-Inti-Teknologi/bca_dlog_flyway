package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRoleMappingRepository
        extends JpaRepository<UserRoleMapping, String>,
                JpaSpecificationExecutor<UserRoleMapping> {
    List<UserRoleMapping> findAllByKeyUserId(String userId);
}