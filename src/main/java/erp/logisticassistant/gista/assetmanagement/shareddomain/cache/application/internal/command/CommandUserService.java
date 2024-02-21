package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.inbound.InboundCompositeRoleService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserRoleMapping;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.UserExternalRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.UserInternalRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.UserRoleMappingRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.UserDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform.UserTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@AllArgsConstructor
public class CommandUserService {

    private final UserTransform userTransform;
    private final UserInternalRepository cacheUserInternalRepository;
    private final UserExternalRepository cacheUserExternalRepository;
    private final UserRoleMappingRepository cacheUserRoleMappingRepository;
    private final InboundCompositeRoleService inboundCacheCompositeRoleService;
    private final ApplicationProperties applicationProperties;

    public Boolean checkProcessingData(UserDto dto){
        AtomicBoolean processingData = new AtomicBoolean(false);
        if(Boolean.TRUE.equals(dto.getInternal())){
            processingData.set(true);
        }
        
        if(Boolean.TRUE.equals(dto.getExternal()) && dto.getUserDetil().getApplicationId() != null && dto.getUserDetil().getApplicationId().equals(applicationProperties.getSpring().getApplication().getId()))
                    processingData.set(true);
        
        return processingData.get();
    }

    @Transactional
    public void processCreateSyncUser(UserDto dtoCreate) {
        dtoCreate.getRoles().forEach(role -> {
            if (!inboundCacheCompositeRoleService.checkCompositeRoleById(role.getCompositeRole().getCompositeRoleId()))
                inboundCacheCompositeRoleService.processCreateCompositeRole(role.getCompositeRole());
        });

        if (Boolean.TRUE.equals(dtoCreate.getInternal()))
            cacheUserInternalRepository.save(userTransform.toCreateCacheUserInternal(dtoCreate.getUserDetil(), dtoCreate));

        if (Boolean.TRUE.equals(dtoCreate.getExternal()))
            cacheUserExternalRepository.save(userTransform.toCreateCacheUserExternal(dtoCreate.getUserDetil(), dtoCreate));

        List<UserRoleMapping> userRoleMappingExists = cacheUserRoleMappingRepository.findAllByKeyUserId(dtoCreate.getUserId());
        if(!userRoleMappingExists.isEmpty())
            cacheUserRoleMappingRepository.deleteAll(userRoleMappingExists);

        if(!dtoCreate.getRoles().isEmpty())
            dtoCreate.getRoles().forEach( userRole -> 
                cacheUserRoleMappingRepository.save(
                        userTransform.toCreateCacheUserRoleMapping(dtoCreate.getUserId(), userRole))
            );
    }

    public void processUpdateSyncUser(UserDto dtoUpdate) {
        dtoUpdate.getRoles().forEach(role -> {
            if (!inboundCacheCompositeRoleService.checkCompositeRoleById(role.getCompositeRole().getCompositeRoleId()))
                inboundCacheCompositeRoleService.processCreateCompositeRole(role.getCompositeRole());
        });

        if (Boolean.TRUE.equals(dtoUpdate.getInternal())) cacheUserInternalRepository.save(
                userTransform.toCreateCacheUserInternal(dtoUpdate.getUserDetil(), dtoUpdate));

        if (Boolean.TRUE.equals(dtoUpdate.getExternal())) cacheUserExternalRepository.save(
                userTransform.toCreateCacheUserExternal(dtoUpdate.getUserDetil(), dtoUpdate));

        List<UserRoleMapping> userRoleMappingExists = cacheUserRoleMappingRepository.findAllByKeyUserId(dtoUpdate.getUserId());
        if(!userRoleMappingExists.isEmpty())
            cacheUserRoleMappingRepository.deleteAll(userRoleMappingExists);

        if(!dtoUpdate.getRoles().isEmpty())
            dtoUpdate.getRoles().forEach( userRole -> 
                cacheUserRoleMappingRepository.save(
                        userTransform.toCreateCacheUserRoleMapping(dtoUpdate.getUserId(), userRole))
            );
    }

    public void processSoftDeleteSyncUser(UserDto dtoDelete) {
        dtoDelete.getRoles().forEach(role -> {
            if (!inboundCacheCompositeRoleService.checkCompositeRoleById(role.getCompositeRole().getCompositeRoleId()))
                inboundCacheCompositeRoleService.processCreateCompositeRole(role.getCompositeRole());
        });

        if (Boolean.TRUE.equals(dtoDelete.getInternal())) cacheUserInternalRepository.save(
                userTransform.toCreateCacheUserInternal(dtoDelete.getUserDetil(), dtoDelete));

        if (Boolean.TRUE.equals(dtoDelete.getExternal())) cacheUserExternalRepository.save(
                userTransform.toCreateCacheUserExternal(dtoDelete.getUserDetil(), dtoDelete));

        List<UserRoleMapping> userRoleMappingExists = cacheUserRoleMappingRepository.findAllByKeyUserId(dtoDelete.getUserId());
        if(!userRoleMappingExists.isEmpty())
            cacheUserRoleMappingRepository.deleteAll(userRoleMappingExists);

        if(!dtoDelete.getRoles().isEmpty())
            dtoDelete.getRoles().forEach( userRole -> 
                cacheUserRoleMappingRepository.save(
                        userTransform.toCreateCacheUserRoleMapping(dtoDelete.getUserId(), userRole))
            );
    }
}