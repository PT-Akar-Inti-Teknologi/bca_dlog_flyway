package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

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
@Table(name = "idm_users_internal", schema = "public")
public class UserInternal extends AuditedMetaData {
  
  @Id
  @Column(name = "user_id")
  private String userId;

  @NotAudited
  @MapsId
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @Column(name = "private_email")
  private String privateEmail;

  @Column(name = "pin")
  private String pin;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "sub_department_code")
  private String subDepartmentCode;

  @Column(name = "sub_department_name")
  private String subDepartmentName;

}
