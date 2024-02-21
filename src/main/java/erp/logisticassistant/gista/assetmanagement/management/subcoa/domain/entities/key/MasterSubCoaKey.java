package erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MasterSubCoaKey implements Serializable {
    @Column(name = "coa_code")
    private String coaCode;
    @Column(name = "sub_coa_code")
    private String subCoaCode;

}
