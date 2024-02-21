package erp.logisticassistant.gista.assetmanagement.management.coa.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.coa.domain.entities.MasterCoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCoaRepository extends JpaRepository<MasterCoa , String>,
        JpaSpecificationExecutor<MasterCoa> {
}
