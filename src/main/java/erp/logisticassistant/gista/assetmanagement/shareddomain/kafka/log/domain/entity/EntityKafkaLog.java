package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity;

import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "kafka_log", schema = "public")
@Data
public class EntityKafkaLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "kafka_log_id_seq")
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "key")
  private String key;

  @Column(name = "topic")
  private String topic;

  @Enumerated(EnumType.STRING)
  @Column(name = "event")
  private EnumKafkaLog event;

  @Column(name = "class_consumer")
  private String classConsumer;

  @Column(name = "description")
  private String description;

  @Column(name = "is_resolved")
  private Boolean isResolved;

  @CreationTimestamp
  @Column(name = "created_at",updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "payload")
  private String payload;

  @Column(name = "log")
  private String log;

}