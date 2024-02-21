package erp.logisticassistant.gista.assetmanagement.management.kodedati.domain.entities;

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
@Table(name = "kode_dati", schema = "public")
public class KodeDati extends FieldSeeder {
}
