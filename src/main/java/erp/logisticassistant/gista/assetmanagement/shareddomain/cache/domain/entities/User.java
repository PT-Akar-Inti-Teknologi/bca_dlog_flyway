package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "idm_users", schema = "public")
public class User extends AuditedMetaData {

  @Id
  @Column(name = "user_id", unique = true)
  private String userId;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "internal")
  private Boolean internal;

  @Column(name = "external")
  private Boolean external;

  @Column(name = "enabled")
  private Boolean enabled;

}
