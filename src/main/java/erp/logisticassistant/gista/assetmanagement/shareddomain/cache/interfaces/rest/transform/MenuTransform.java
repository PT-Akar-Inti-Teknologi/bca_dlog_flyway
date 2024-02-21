package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Menu;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.MenuDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class,
        ApplicationTransform.class
})
public interface MenuTransform {

    @Named("toCreateCacheMenu")
    @Mapping( target = "menuParentId", source = "request.menuParent.menuId")
    @Mapping( target = "applicationId", source = "request.application.applicationId")
    @Mapping( target = "application", source = "request.application", qualifiedByName = "toCreateCacheApplication")
    @Mapping( target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    Menu toCreateCacheMenu(MenuDto request);
}
