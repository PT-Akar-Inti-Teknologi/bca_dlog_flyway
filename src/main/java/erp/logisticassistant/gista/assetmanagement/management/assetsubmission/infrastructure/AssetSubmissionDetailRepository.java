package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmissionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetSubmissionDetailRepository extends JpaRepository<AssetSubmissionDetail, Long>,
        JpaSpecificationExecutor<AssetSubmissionDetail> {
    Page<AssetSubmissionDetail> findAllByNomorRequest(String nomorRequest , Pageable pageable);
}
