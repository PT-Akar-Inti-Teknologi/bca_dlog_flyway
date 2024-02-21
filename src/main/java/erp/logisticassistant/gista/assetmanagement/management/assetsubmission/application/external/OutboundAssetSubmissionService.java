package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.external;

import erp.logisticassistant.gista.assetmanagement.management.branch.application.internal.QueryMasterBranchMidtier;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.application.internal.command.CommandCostCenterService;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboundAssetSubmissionService {
    private final CommandCostCenterService commandCostCenterService;
    private final QueryMasterBranchMidtier queryMasterBranchMidtier;

    public void setValidated(AtomicBoolean atomicSetData, Boolean data) {
        atomicSetData.set(data);
    }

    public Boolean isValidCostCenterId(String costCenterId){
        AtomicBoolean result = new AtomicBoolean(false);
        Optional<MasterCostCenter> data =  commandCostCenterService.findByCostCenterId(costCenterId);
        if (data.isPresent() && LocalDate.now().isAfter(data.get().getValidTo())){
            result.set(true);
        }
        return result.get();
    }

    public List<String> getAllBranchRelated(String branchCode){
        return queryMasterBranchMidtier.getAllBranchPathByBranchCode(branchCode);
    }
}
