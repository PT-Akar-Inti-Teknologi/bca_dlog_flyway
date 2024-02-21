package erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities;

import erp.logisticassistant.gista.assetmanagement.management.coa.domain.entities.MasterCoa;
import erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities.key.MasterSubCoaKey;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sub_coa", schema = "masterdata")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class MasterSubCoa extends AuditEnabledEntity {
    @EmbeddedId
    private MasterSubCoaKey id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "coa_code", referencedColumnName = "coa_code", insertable = false, updatable = false)
    private MasterCoa masterCoa;
    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "coa_code", referencedColumnName = "coa_code", insertable = false, updatable = false),
            @JoinColumn(name = "sub_coa_code", referencedColumnName = "sub_coa_code", insertable = false, updatable = false)
    })
    private MasterSubCoa subCoa;
    @Column(name = "description")
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
}
