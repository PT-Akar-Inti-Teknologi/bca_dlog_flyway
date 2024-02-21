package erp.logisticassistant.gista.assetmanagement.management.attachment.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.attachment.domain.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String>,
        JpaSpecificationExecutor<Attachment> {
}
