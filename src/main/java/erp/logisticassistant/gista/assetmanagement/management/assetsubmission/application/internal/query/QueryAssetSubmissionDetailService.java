package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmissionDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.infrastructure.AssetSubmissionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryAssetSubmissionDetailService {
    private final AssetSubmissionDetailRepository assetSubmissionDetailRepository;
    public Page<AssetSubmissionDetail> getDetailSubmission(String requestNo, Pageable pageable){
        return assetSubmissionDetailRepository.findAllByNomorRequest(requestNo, pageable);
    }

}
