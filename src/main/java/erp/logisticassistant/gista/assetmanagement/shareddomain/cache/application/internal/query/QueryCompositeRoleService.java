package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.CompositeRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.CompositeRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class QueryCompositeRoleService {

    private final CompositeRoleRepository compositeRoleRepository;

    public Optional<CompositeRole> findCompositeRoleById(String compositeRoleId) {
        return compositeRoleRepository.findByCompositeRoleId(compositeRoleId);
    }
}