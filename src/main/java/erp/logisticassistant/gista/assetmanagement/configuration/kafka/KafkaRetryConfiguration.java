package erp.logisticassistant.gista.assetmanagement.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.application.internal.command.ImplInternalCommandKafkaLogService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.domain.entity.enumeration.EnumKafkaLog;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages.KafkaProducerService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.KafkaConsumerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class KafkaRetryConfiguration {

    private final ImplInternalCommandKafkaLogService commandKafkaConsumerLogService;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper mapper;
    private final ApplicationProperties applicationProperties;

    private KafkaConsumerException kafkaConsumerException;

    @Bean
    ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setRetryTemplate(retryTemplate());
        factory.setRecoveryCallback((context -> {
            kafkaConsumerException = getConsumerException(context.getLastThrowable().getCause());
            if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException){
                //here you can do your recovery mechanism where you can put back on to the topic using a Kafka producer
            } else{
                // here you can log things and throw some custom exception that Error handler will take care of ..
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }

            return null;
        }));
        factory.setErrorHandler(((exception, data) -> {
            log.error("Error in process with Exception {} and the record is {}", exception, data);
            //to do recovery or create log
            if (Boolean.TRUE.equals(kafkaConsumerException.getTechnicalFailure()) && kafkaConsumerException.getRetryCount() <= applicationProperties.getSpring().getKafka().getConsumer().getMaxRetry()){
                kafkaProducerService.produceRetry( data, kafkaConsumerException.getRetryCount(), kafkaConsumerException.getClassName(), kafkaConsumerException.getKey(), kafkaConsumerException.getPayload());
            } else {
                commandKafkaConsumerLogService.doCreateLog(kafkaConsumerException, EnumKafkaLog.CONSUME);
            }
            
        }));
        return factory;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
       /* here retry policy is used to set the number of attempts to retry and what exceptions you wanted to try and what you don't want to retry.*/
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }
    
    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(IllegalArgumentException.class, true);
        exceptionMap.put(TimeoutException.class, true);
        exceptionMap.put(GlobalException.class, true);
        return new SimpleRetryPolicy(1,exceptionMap,true);
    }

    private KafkaConsumerException getConsumerException(Object data){
        return mapper.convertValue(data, KafkaConsumerException.class);
    }
}
