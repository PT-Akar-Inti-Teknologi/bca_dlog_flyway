package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRoleRepository
        extends JpaRepository<ApplicationRole, String>,
        RevisionRepository<ApplicationRole, String, Long>,
        JpaSpecificationExecutor<ApplicationRole> {
    Optional<ApplicationRole> findByApplicationRoleId(String applicationRoleId);
}
