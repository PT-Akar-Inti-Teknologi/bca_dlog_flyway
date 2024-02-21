package erp.logisticassistant.gista.assetmanagement.management.attachment.domain.entities;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmission;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "attachment", schema = "public")
public class Attachment extends AuditEnabledEntity {
    @Id
    @Column(name = "document_no")
    private String documentNo;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "description")
    private String description;
    @Column(name = "search_string")
    private String searchString;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "nomor_request", insertable = false, updatable = false)
    @NotAudited
    private AssetSubmission attachmentSubmissionList;

}
