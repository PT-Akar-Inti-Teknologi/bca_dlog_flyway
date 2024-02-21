package erp.logisticassistant.gista.assetmanagement.shareddomain.util.base;

import erp.logisticassistant.gista.assetmanagement.configuration.datasource.AuditRevisionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "revision_info")
@AttributeOverrides({
        @AttributeOverride(name = "timestamp", column = @Column(name = "timestamp")),
        @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
})
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @NotBlank
    private String username;

}