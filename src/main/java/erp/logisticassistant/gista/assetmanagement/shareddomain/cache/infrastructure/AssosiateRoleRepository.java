package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AssosiateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssosiateRoleRepository
        extends JpaRepository<AssosiateRole, String>,
        RevisionRepository<AssosiateRole, String, Long>,
        JpaSpecificationExecutor<AssosiateRole> {
    AssosiateRole findByKeyApplicationRoleIdAndKeyCompositeRoleId(String applicationRoleId, String compositeRoleId);

    @Query(value = "SELECT iar.* FROM idm_assosiate_role iar " +
            "LEFT JOIN idm_user_role_mapping us on us.composite_role_id = iar.composite_role_id " +
            "LEFT JOIN idm_users u on u.user_id = us.user_id " +
            "WHERE u.user_id=:userId " , nativeQuery = true)
    List<AssosiateRole> findByKeyUserId(String userId);
}
