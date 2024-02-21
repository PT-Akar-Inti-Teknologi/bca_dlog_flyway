package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "idm_access_role_mapping", schema = "public")
public class AccessRole extends AuditedMetaData {
  @Id
  @Column(name = "access_id")
  private String accessId;

  @Column(name = "menu_id")
  private String menuId;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "menu_id", referencedColumnName = "menu_id", insertable = false, updatable = false)
  Menu menu;

  @Column(name = "application_role_id")
  private String applicationRoleId;

  @Column(name = "application_role_name")
  private String applicationRoleName;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "application_role_id", referencedColumnName = "application_role_id", insertable = false, updatable = false)
  ApplicationRole applicationRole;

  @Column(name = "application_id")
  private String applicationId;

  @Column(name = "can_create")
  private Boolean create;

  @Column(name = "can_read")
  private Boolean read;

  @Column(name = "can_update")
  private Boolean update;

  @Column(name = "can_delete")
  private Boolean delete;

  @Column(name = "enabled")
  private Boolean enabled;

}
