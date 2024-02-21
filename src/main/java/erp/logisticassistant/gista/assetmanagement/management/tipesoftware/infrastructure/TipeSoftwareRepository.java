package erp.logisticassistant.gista.assetmanagement.management.tipesoftware.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.tipesoftware.domain.entities.TipeSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeSoftwareRepository extends JpaRepository<TipeSoftware, String>,
        JpaSpecificationExecutor<TipeSoftware> {
}
