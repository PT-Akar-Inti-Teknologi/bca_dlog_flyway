package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.ApplicationRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.ApplicationRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class QueryApplicationRoleService {

    private final ApplicationRoleRepository applicationRoleRepository;

    public Optional<ApplicationRole> findApplicationRoleById(String applicationRoleId) {
        return applicationRoleRepository.findByApplicationRoleId(applicationRoleId);
    }
}