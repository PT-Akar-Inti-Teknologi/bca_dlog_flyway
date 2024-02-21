package erp.logisticassistant.gista.assetmanagement.management.user.application;

import erp.logisticassistant.gista.assetmanagement.management.user.domain.entities.BaseUserInternal;
import erp.logisticassistant.gista.assetmanagement.management.user.infrastructure.BaseUserInternalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryBaseUserInternal {
    private final BaseUserInternalRepository baseUserInternalRepository;
    public String getSubDepartmentCode(String username){
        BaseUserInternal userInternal = baseUserInternalRepository.findByUserUsernameIgnoreCase(username)
                .orElse(null);
        if (userInternal == null)
            return null;
        return userInternal.getSubDepartmentCode();
    }
}
