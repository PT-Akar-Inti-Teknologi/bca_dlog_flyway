package erp.logisticassistant.gista.assetmanagement.management.user.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.User;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserRoleMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "idm_users_internal", schema = "public")
public class BaseUserInternal {

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<UserRoleMapping> roles;
}
