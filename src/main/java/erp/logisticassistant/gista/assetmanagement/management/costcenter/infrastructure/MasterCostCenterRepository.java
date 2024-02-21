package erp.logisticassistant.gista.assetmanagement.management.costcenter.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterCostCenterRepository extends JpaRepository<MasterCostCenter, String>,
        JpaSpecificationExecutor<MasterCostCenter> {
    Optional<MasterCostCenter> findByCostCenterId(String costCenterId);
    @Query("SELECT cs FROM MasterCostCenter cs WHERE SUBSTRING(cs.costCenterId, 1,2) = :substring")
    Optional<MasterCostCenter> findByCostCenterIdPrefix(@Param("substring") String costCenterId);
    @Query("SELECT cs FROM MasterCostCenter cs WHERE SUBSTRING(cs.costCenterId, 3) = :substring")
    Optional<MasterCostCenter> findByCostCenterIdPrefixOrganizationCodeWithRcc(@Param("substring") String costCenterId);
    @Query("SELECT cs FROM MasterCostCenter cs WHERE SUBSTRING(cs.costCenterId, 7) = :substring")
    Optional<MasterCostCenter> findByCostCenterIdPrefixRcc(@Param("substring") String costCenterId);
}
