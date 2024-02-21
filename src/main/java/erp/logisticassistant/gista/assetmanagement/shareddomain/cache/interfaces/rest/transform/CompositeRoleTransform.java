package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.CompositeRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.CompositeRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface CompositeRoleTransform {

    @Named("toCreateCacheCompositeRole")
    @Mapping( target = "name", source = "request.name")
    @Mapping( target = "description", source = "request.description")
    @Mapping( target = "enabled", source = "request.enabled")
    @Mapping( target = "version", source = "request.version")
    @Mapping( target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    CompositeRole toCreateCacheCompositeRole(CompositeRoleDto request);

}