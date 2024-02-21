package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class AssosiateKey implements Serializable {

  @Column(name = "composite_role_id")
  String compositeRoleId;

  @Column(name = "application_role_id")
  String applicationRoleId;

}
