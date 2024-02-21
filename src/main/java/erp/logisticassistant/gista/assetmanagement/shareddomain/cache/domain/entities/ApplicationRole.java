package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "idm_application_role", schema = "public")
@Data
public class ApplicationRole extends AuditedMetaData {
  @Id
  @Column(name = "application_role_id")
  private String applicationRoleId;

  @Column(name = "application_id")
  private String applicationId;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "application_id", referencedColumnName = "application_id", insertable = false, updatable = false)
  Application application;

  @NotAudited
  @JsonManagedReference
  @OneToMany(mappedBy = "applicationRole", targetEntity = AccessRole.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AccessRole> accessRoles;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "role_sap")
  private Boolean roleSap;

  @Column(name = "enabled")
  private Boolean enabled;

}
