package erp.logisticassistant.gista.assetmanagement.management.branch.application.internal;

import erp.logisticassistant.gista.assetmanagement.management.branch.domain.entities.MasterBranchMidTier;
import erp.logisticassistant.gista.assetmanagement.management.branch.infrastructure.MasterBranchMidtierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueryMasterBranchMidtier {
    private final MasterBranchMidtierRepository masterBranchMidtierRepository;
    public List<String> getAllBranchPathByBranchCode(String branchCode){
        Optional<MasterBranchMidTier> branchMidTier = masterBranchMidtierRepository.findById(branchCode);
        return branchMidTier.map(masterBranchMidTier -> Arrays.asList(masterBranchMidTier.getPath())).orElseGet(ArrayList::new);
    }
}
