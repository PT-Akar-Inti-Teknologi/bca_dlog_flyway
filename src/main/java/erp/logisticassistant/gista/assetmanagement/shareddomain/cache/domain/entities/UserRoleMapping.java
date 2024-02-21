package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.key.UserRoleRoleKey;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "idm_user_role_mapping", schema = "public")
@Data
public class UserRoleMapping extends AuditedMetaData {

  @EmbeddedId
  UserRoleRoleKey key;

  @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumn(name = "composite_role_id", referencedColumnName = "composite_role_id", insertable = false, updatable = false)
  CompositeRole compositeRole;

  @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
  User user;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "enabled")
  private Boolean enabled;

}