package erp.logisticassistant.gista.assetmanagement.management.costcenter.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.management.costcenter.application.internal.query.QueryCostCenterService;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.infrastructure.MasterCostCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandCostCenterService {
    private final QueryCostCenterService queryCostCenterService;

    public Optional<MasterCostCenter> findByCostCenterId(String costCenterId) {
        return queryCostCenterService.findCostCenterId(costCenterId);
    }

}
