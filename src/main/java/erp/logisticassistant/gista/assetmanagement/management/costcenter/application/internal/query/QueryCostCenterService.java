package erp.logisticassistant.gista.assetmanagement.management.costcenter.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.infrastructure.MasterCostCenterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class QueryCostCenterService {
    private final MasterCostCenterRepository masterCostCenterRepository;
    public Optional<MasterCostCenter> findCostCenterId(String costCenterId) {
        return masterCostCenterRepository.findByCostCenterId(costCenterId);
    }
    public MasterCostCenter getByCostCenterPrefix(String costCenterPrefix){
        return masterCostCenterRepository.findByCostCenterIdPrefix(costCenterPrefix)
                .orElse(null);
    }
    public MasterCostCenter getByCostCenterPrefixOrganizationCodeWithRcc(String organizationCode){
        return masterCostCenterRepository.findByCostCenterIdPrefixOrganizationCodeWithRcc(organizationCode)
                .orElse(null);
    }
    public MasterCostCenter getByCostCenterPrefixRcc(String rcc){
        return masterCostCenterRepository.findByCostCenterIdPrefixRcc(rcc)
                .orElse(null);
    }

}
