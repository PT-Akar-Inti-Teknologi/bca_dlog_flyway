package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.User;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserExternal;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserInternal;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserRoleMapping;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.UserDetailDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.UserDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.UserRoleDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", uses = {
    ConvertTransform.class
})
public interface UserTransform {

    @Named("toCacheUserEntity")
    @Mapping( target = "enabled", source = "user.enabled")
    @Mapping( target = "version", source = "user.version")
    @Mapping( target = "createdBy", source = "user.createdBy")
    @Mapping( target = "modifiedBy", source = "user.modifiedBy")
    @Mapping( target = "createdAt", source = "user.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "user.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    User toCreateCacheUser(UserDto user);

    @Named("toCacheUserInternalEntity")
    @Mapping( target = "user", source = "user", qualifiedByName = "toCacheUserEntity")
    @Mapping( target = "createdBy", source = "user.createdBy")
    @Mapping( target = "modifiedBy", source = "user.modifiedBy")
    @Mapping( target = "createdAt", source = "user.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "user.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "userId", source = "user.userId")
    UserInternal toCreateCacheUserInternal(
        UserDetailDto userDetail,
        UserDto user);

    @Named("toCreateCacheUserExternal")
    @Mapping( target = "user", source = "user", qualifiedByName = "toCacheUserEntity")
    @Mapping( target = "createdBy", source = "user.createdBy")
    @Mapping( target = "modifiedBy", source = "user.modifiedBy")
    @Mapping( target = "createdAt", source = "user.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "user.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "birthDate", source = "userDetail.birthDate", qualifiedByName = "stringToLocalDate")
    @Mapping( target = "applicationId", source = "userDetail.applicationId")
    @Mapping( target = "userId", source = "user.userId")
    UserExternal toCreateCacheUserExternal(
        UserDetailDto userDetail,
        UserDto user);


    @Named("toCreateCacheUserRoleMapping")
    @Mapping( target = "key.userId", source = "userId")
    @Mapping( target = "key.compositeRoleId", source = "userRole.compositeRole.compositeRoleId")
    @Mapping( target = "user", ignore = true)
    @Mapping( target = "compositeRole", ignore = true)
    @Mapping( target = "startDate", source = "userRole.startDate", qualifiedByName = "stringToLocalDate")
    @Mapping( target = "endDate", source = "userRole.endDate", qualifiedByName = "stringToLocalDate")
    @Mapping( target = "enabled", source = "userRole.enabled")
    @Mapping( target = "version", source = "userRole.version")
    @Mapping( target = "createdBy", source = "userRole.createdBy")
    @Mapping( target = "modifiedBy", source = "userRole.modifiedBy")
    @Mapping( target = "createdAt", source = "userRole.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "userRole.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    UserRoleMapping toCreateCacheUserRoleMapping(
        String userId,
        UserRoleDto userRole);
}