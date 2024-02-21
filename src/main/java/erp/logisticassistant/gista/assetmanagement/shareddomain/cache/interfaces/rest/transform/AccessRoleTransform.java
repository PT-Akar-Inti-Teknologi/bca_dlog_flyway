package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AccessRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.AccessRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ApplicationRoleTransform.class,
        ConvertTransform.class,
        MenuTransform.class
})
public interface AccessRoleTransform {

    @Named("toCreateCacheAccessRole")
    @Mapping( target = "create", source = "request.create")
    @Mapping( target = "read", source = "request.read")
    @Mapping( target = "update", source = "request.update")
    @Mapping( target = "delete", source = "request.delete")
    @Mapping( target = "menuId", source = "request.menu.menuId")
    @Mapping( target = "menu", ignore = true)
    @Mapping( target = "applicationRoleId", source = "request.applicationRole.applicationRoleId")
    @Mapping( target = "applicationRoleName", source = "request.applicationRole.name")
    @Mapping( target = "applicationRole", ignore = true)
    @Mapping( target = "enabled", source = "request.enabled")
    @Mapping( target = "version", source = "request.version")
    @Mapping( target = "applicationId", source = "request.applicationRole.application.applicationId")
    @Mapping( target = "createdBy", source = "request.createdBy")
    @Mapping( target = "modifiedBy", source = "request.modifiedBy")
    @Mapping( target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    AccessRole toCreateCacheAccessRole(AccessRoleDto request);

}
