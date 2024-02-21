package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.key.AssosiateKey;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "idm_assosiate_role", schema = "public")
@Data
public class AssosiateRole extends AuditedMetaData {

  @EmbeddedId
  AssosiateKey key;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "composite_role_id", referencedColumnName = "composite_role_id", insertable = false, updatable = false)
  CompositeRole compositeRole;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "application_role_id", referencedColumnName = "application_role_id", insertable = false, updatable = false)
  ApplicationRole applicationRole;

  @Column(name = "composite_role_name")
  private String compositeRoleName;

  @Column(name = "application_role_name")
  private String applicationRoleName;

  @Column(name = "enabled")
  private Boolean enabled;
}
