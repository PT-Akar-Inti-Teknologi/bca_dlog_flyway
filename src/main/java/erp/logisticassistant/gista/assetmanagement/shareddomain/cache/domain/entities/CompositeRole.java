package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "idm_composite_role", schema = "public")
@Data
public class CompositeRole extends AuditedMetaData {

    @Id
    @Column(name = "composite_role_id")
    private String compositeRoleId;

    @NotAudited
    @JsonManagedReference
    @OneToMany(mappedBy = "compositeRole", targetEntity = AssosiateRole.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssosiateRole> assosiateRoleList;

    @NotAudited
    @JsonManagedReference
    @OneToMany(mappedBy = "compositeRole", targetEntity = UserRoleMapping.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRoleMapping> userList;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private Boolean enabled;

}