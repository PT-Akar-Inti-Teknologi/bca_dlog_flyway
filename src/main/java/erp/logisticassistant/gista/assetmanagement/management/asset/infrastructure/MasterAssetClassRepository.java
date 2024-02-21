package erp.logisticassistant.gista.assetmanagement.management.asset.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.asset.domain.entities.MasterAssetClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAssetClassRepository extends JpaRepository<MasterAssetClass, String>,
        JpaSpecificationExecutor<MasterAssetClass> {
}
