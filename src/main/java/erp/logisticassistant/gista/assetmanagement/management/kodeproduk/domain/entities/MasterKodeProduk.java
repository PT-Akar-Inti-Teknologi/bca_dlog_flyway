package erp.logisticassistant.gista.assetmanagement.management.kodeproduk.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "kode_produk", schema = "masterdata")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class MasterKodeProduk extends AuditEnabledEntity {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
