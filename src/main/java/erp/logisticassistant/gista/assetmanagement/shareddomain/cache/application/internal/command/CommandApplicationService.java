package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.ApplicationRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.ApplicationTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandApplicationService {
    
    private final ApplicationTransform applicationRoleTransform;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void processCreateApplication(ApplicationDto dto) {
        applicationRepository.save(applicationRoleTransform.toCreateCacheApplication(dto));
    }

    @Transactional
    public void processUpdateApplication(ApplicationDto dto) {
        applicationRepository.save(applicationRoleTransform.toCreateCacheApplication(dto));
    }

    @Transactional
    public void processDeleteApplication(ApplicationDto dto) {
        applicationRepository.save(applicationRoleTransform.toCreateCacheApplication(dto));
    }
}