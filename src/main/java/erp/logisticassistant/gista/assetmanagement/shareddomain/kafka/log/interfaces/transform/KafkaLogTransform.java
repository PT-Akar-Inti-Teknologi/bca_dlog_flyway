package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.EntityKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.DeathLetterTopicDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaConsumerRecordDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.dto.KafkaLogDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface KafkaLogTransform {

    @Named("toKafkaConsumerLogEntity")
    @Mapping(target = "key",  expression = "java(consumerRecordDto.getKey() == null ? \"\" : consumerRecordDto.getKey() )")
    @Mapping(target = "topic",  expression = "java(consumerRecordDto.getTopic() == null ? \"\" : consumerRecordDto.getTopic() )")
    @Mapping(target = "payload" , source = "consumerRecordDto.value")
    @Mapping(target = "isResolved", constant = "false")
    @Mapping(target = "classConsumer", source = "classConsumer")
    EntityKafkaLog toCreateLog(KafkaConsumerRecordDto consumerRecordDto, KafkaLogDto dto,
                               EnumKafkaLog event, String log, String description, String classConsumer);

    @Mapping(target = "key", source = "key")
    @Mapping(target = "topic",  source = "topic")
    @Mapping(target = "isResolved", constant = "false")                       
    EntityKafkaLog toCreateProducerLog(String key, String topic, EnumKafkaLog event, String log, String payload);

    @Mapping(target = "key",  expression = "java(dto.getKey() == null ? \"\" : dto.getKey() )")
    @Mapping(target = "topic",  expression = "java(dto.getTopic() == null ? \"\" : dto.getTopic() )")
    @Mapping(target = "payload" , source = "dto.payload")
    @Mapping(target = "isResolved", constant = "false")
    @Mapping(target = "classConsumer", source = "exception.className")
    @Mapping(target = "log", source = "exception.message")
    EntityKafkaLog toCreateLog(KafkaConsumerException exception, KafkaLogDto dto, EnumKafkaLog event);

    @Mapping(target = "key",  expression = "java(exception.getKey() == null ? \"\" : exception.getKey() )")
    @Mapping(target = "topic",  expression = "java(exception.getTopic() == null ? \"\" : exception.getTopic() )")
    @Mapping(target = "payload" , source = "exception.payload")
    @Mapping(target = "log", source = "exception.message")
    KafkaLogDto toKafkaLogFromException(KafkaConsumerException exception);

    @Mapping( target = "createdAt", source = "entity.createdAt", qualifiedByName = "localDateTimetoString")
    @Mapping(target = "retryTopic", source = "retryTopic")
    DeathLetterTopicDto toCreateDeathLetterTopic(EntityKafkaLog entity, String retryTopic);
}


