package erp.logisticassistant.gista.assetmanagement.management.tipeat.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.seeder.domain.entities.FieldSeeder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "tipe_at", schema = "public")
public class TipeAt extends FieldSeeder {
}
