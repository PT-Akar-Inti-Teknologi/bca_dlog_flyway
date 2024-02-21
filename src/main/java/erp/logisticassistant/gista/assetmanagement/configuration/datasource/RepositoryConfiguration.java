package erp.logisticassistant.gista.assetmanagement.configuration.datasource;

import erp.logisticassistant.gista.assetmanagement.configuration.security.handler.SecurityExpressionHandler;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@RequiredArgsConstructor
public class RepositoryConfiguration {

    private final SecurityExpressionHandler expressionHandler;
    private final ApplicationProperties applicationProperties;

    @Bean
    AuditorAware<String> auditorProviders(){
        return new AuditorAwareImpl(expressionHandler, applicationProperties);
    }
}
