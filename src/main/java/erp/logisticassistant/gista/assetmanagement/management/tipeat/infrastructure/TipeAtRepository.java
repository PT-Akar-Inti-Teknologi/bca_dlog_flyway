package erp.logisticassistant.gista.assetmanagement.management.tipeat.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.tipeat.domain.entities.TipeAt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeAtRepository extends JpaRepository<TipeAt, String>,
        JpaSpecificationExecutor<TipeAt> {
}
