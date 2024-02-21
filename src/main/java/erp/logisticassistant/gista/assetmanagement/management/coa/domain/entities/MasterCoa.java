package erp.logisticassistant.gista.assetmanagement.management.coa.domain.entities;

import erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities.MasterSubCoa;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "coa", schema = "masterdata")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class MasterCoa extends AuditEnabledEntity {
    @Id
    @Column(name = "coa_code")
    private String coaCode;

    @Column(name = "description")
    private String description;

    @Column(name = "is_slid")
    private Boolean isSlid;

    @Column(name = "coa_type")
    private String coaType;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "is_valas")
    private Boolean isValas;

    @NotAudited
    @OneToMany(mappedBy = "masterCoa", targetEntity = MasterSubCoa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MasterSubCoa> masterSubCoa;
}
