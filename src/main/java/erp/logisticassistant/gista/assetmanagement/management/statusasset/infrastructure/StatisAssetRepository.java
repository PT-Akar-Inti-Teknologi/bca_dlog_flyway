package erp.logisticassistant.gista.assetmanagement.management.statusasset.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.statusasset.domain.entities.StatusAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisAssetRepository extends JpaRepository<StatusAsset, String>,
        JpaSpecificationExecutor<StatusAsset> {
}
