package erp.logisticassistant.gista.assetmanagement.management.branch.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.branch.domain.entities.MasterBranchMidTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBranchMidtierRepository extends JpaRepository<MasterBranchMidTier, String> , JpaSpecificationExecutor<MasterBranchMidTier> {
}
