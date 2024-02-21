package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AssosiateRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.AssosiateRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class,
        AssosiateRoleTransform.class,
        CompositeRoleTransform.class
})
public interface AssosiateRoleTransform {

    @Named("toCreateCacheAssosiateRole")
    @Mapping( target = "key.compositeRoleId", source = "request.compositeRole.compositeRoleId")
    @Mapping( target = "key.applicationRoleId", source = "request.applicationRole.applicationRoleId")
    @Mapping( target = "compositeRoleName", source = "request.compositeRole.name")
    @Mapping( target = "applicationRoleName", source = "request.applicationRole.name")
    @Mapping(target = "compositeRole", ignore = true)
    @Mapping(target = "applicationRole", ignore = true)
    @Mapping(target = "enabled", source = "request.enabled")
    @Mapping(target = "version", source = "request.version")
    @Mapping(target = "createdBy", source = "request.createdBy")
    @Mapping(target = "modifiedBy", source = "request.modifiedBy")
    @Mapping(target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    AssosiateRole toCreateCacheAssosiateRole(AssosiateRoleDto request);

}
