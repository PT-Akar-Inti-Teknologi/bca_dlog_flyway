package erp.logisticassistant.gista.assetmanagement.management.tipesoftware.domain.entities;

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
@Table(name = "tipe_software", schema = "public")
public class TipeSoftware extends FieldSeeder {
}
