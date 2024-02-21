package erp.logisticassistant.gista.assetmanagement.management.kodekualitas.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.kodekualitas.domain.entities.KodeKualitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KodeKualitasRepository extends JpaRepository<KodeKualitas, String>,
        JpaSpecificationExecutor<KodeKualitas> {
}
