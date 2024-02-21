package erp.logisticassistant.gista.assetmanagement.shareddomain.commons.seeder.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public class FieldSeeder extends AuditEnabledEntity {
    @Id
    private String kode;
    private String deskripsi;
}
