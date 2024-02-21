package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.service;

import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.messages.ConsumerCommandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallbackBeanFactory {

    private final BeanFactory beanFactory;

    public void doRetryKafka(String className, String message){
        ConsumerCommandService kafkaService = beanFactory.getBean(className, ConsumerCommandService.class);
        kafkaService.consume(message);
    }
}
