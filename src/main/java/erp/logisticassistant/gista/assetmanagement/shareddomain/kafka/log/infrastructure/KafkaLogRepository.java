package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.EntityKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KafkaLogRepository
        extends JpaRepository<EntityKafkaLog, Long>, JpaSpecificationExecutor<EntityKafkaLog> {

    List<EntityKafkaLog> findAllByEventAndIsResolved(EnumKafkaLog event, Boolean isResolved);
}
