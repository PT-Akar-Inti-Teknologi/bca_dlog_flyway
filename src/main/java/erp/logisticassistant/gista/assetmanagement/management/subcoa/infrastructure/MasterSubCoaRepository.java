package erp.logisticassistant.gista.assetmanagement.management.subcoa.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities.MasterSubCoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterSubCoaRepository extends JpaRepository<MasterSubCoa, Long>,
        JpaSpecificationExecutor<MasterSubCoa> {
}
