package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.ApplicationRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class,
        ApplicationTransform.class
})
public interface ApplicationRoleTransform {

    @Named("toCreateCacheApplicationRole")
    @Mapping( target = "applicationId", source = "request.application.applicationId")
    @Mapping( target = "version", source = "request.version")
    @Mapping( target = "application", source = "request.application", qualifiedByName = "toCreateCacheApplication")
    @Mapping( target = "createdBy", source = "request.createdBy")
    @Mapping( target = "modifiedBy", source = "request.modifiedBy")
    @Mapping( target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    ApplicationRole toCreateCacheApplicationRole(ApplicationRoleDto request);

}
