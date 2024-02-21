package erp.logisticassistant.gista.assetmanagement.management.kodeproduk.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.kodeproduk.domain.entities.MasterKodeProduk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterKodeProdukRepository extends JpaRepository<MasterKodeProduk, String>,
        JpaSpecificationExecutor<MasterKodeProduk> {
}
