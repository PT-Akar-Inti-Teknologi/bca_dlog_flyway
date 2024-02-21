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
@Data
@Entity
@Table(name = "idm_menu", schema = "public")
public class Menu extends AuditedMetaData {
  @Id
  @Column(name = "menu_id")
  private String menuId;

  @NotAudited
  @JsonManagedReference
  @OneToMany(mappedBy = "menu", targetEntity = AccessRole.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AccessRole> accessRoles;

  @Column(name = "application_id")
  private String applicationId;

  @NotAudited
  @JsonBackReference
  @ManyToOne(optional=false)
  @JoinColumn(name = "application_id", referencedColumnName = "application_id", insertable = false, updatable = false)
  private Application application;

  @Column(name = "sub_category")
  private String subCategory;

  @Column(name = "name")
  private String name;

  @Column(name = "parent")
  private Boolean parent;

  @Column(name = "menu_parent_id")
  private String menuParentId;

  @Column(name = "endpoint")
  private String endpoint;

  @Column(name = "can_create")
  private Boolean create;

  @Column(name = "can_read")
  private Boolean read;

  @Column(name = "can_update")
  private Boolean update;

  @Column(name = "can_delete")
  private Boolean delete;

  @Column(name = "private")
  private Boolean pvt;

  @Column(name = "enabled")
  private Boolean enabled;

}
