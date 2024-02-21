package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccessRoleRepository
        extends JpaRepository<AccessRole, String>,
        RevisionRepository<AccessRole, String, Long>,
                JpaSpecificationExecutor<AccessRole> {

    List<AccessRole> findAllByMenuMenuIdInAndApplicationRoleNameInAndDeleteTrueAndEnabledTrue(List<String> menuAccess, ArrayList<String> roles);
    List<AccessRole> findAllByMenuMenuIdInAndApplicationRoleNameInAndUpdateTrueAndEnabledTrue(List<String> menuAccess, ArrayList<String> roles);
    List<AccessRole> findAllByMenuMenuIdInAndApplicationRoleNameInAndReadTrueAndEnabledTrue(List<String> menuAccess, ArrayList<String> roles);
    List<AccessRole> findAllByMenuMenuIdInAndApplicationRoleNameInAndCreateTrueAndEnabledTrue(List<String> menuAccess, ArrayList<String> roles);
}
