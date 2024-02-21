package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository
        extends JpaRepository<Application, String>,
        RevisionRepository<Application, String, Long>,
        JpaSpecificationExecutor<Application> {
    Optional<Application> findByApplicationId(String applicationId);
}
