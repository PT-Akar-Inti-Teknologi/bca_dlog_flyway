package erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cost_center", schema = "masterdata")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class MasterCostCenter extends AuditEnabledEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name="cost_center_id")
    private String costCenterId;
    @Column(name="description")
    private String description;
    @Column(name="cost_center_parent")
    private String costCenterParent;
    @Column(name="business_number")
    private String businessNumber;
    @Column(name="branch_type")
    private String branchType;
    @Column(name="valid_from")
    private LocalDate validFrom;
    @Column(name="valid_to")
    private LocalDate validTo;
}
