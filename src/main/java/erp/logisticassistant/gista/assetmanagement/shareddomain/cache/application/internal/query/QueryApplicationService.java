package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Application;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.ApplicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class QueryApplicationService {

    private final ApplicationRepository applicationRepository;

    public Optional<Application> findApplicationById(String applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
    }
}