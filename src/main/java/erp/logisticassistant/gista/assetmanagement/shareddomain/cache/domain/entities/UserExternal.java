package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "idm_users_external", schema = "public")
public class UserExternal extends AuditedMetaData {
  
  @Id
  @Column(name = "user_id")
  private String userId;

  @NotAudited
  @MapsId
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @Column(name = "application_id")
  private String applicationId;

  @Column(name = "is_employees")
  private Boolean isEmployees;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "nik")
  private String nik;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "company_code")
  private String companyCode;

  @Column(name = "vendor_code")
  private String vendorCode;

  @Column(name = "company_name")
  private String companyName;

}
