package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "idm_application", schema = "public")
public class Application extends AuditedMetaData {
  @Id
  @Column(name = "application_id")
  private String applicationId;

  @NotAudited
  @JsonManagedReference
  @OneToMany(mappedBy = "application", targetEntity = ApplicationRole.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ApplicationRole> applicationRoleList;

  @NotAudited
  @JsonManagedReference
  @OneToMany(mappedBy = "application", targetEntity = Menu.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Menu> menuList;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "desktop")
  private Boolean desktop;

  @Column(name = "mobile")
  private Boolean mobile;

  @Column(name = "type_internet")
  private Boolean typeInternet;
}
