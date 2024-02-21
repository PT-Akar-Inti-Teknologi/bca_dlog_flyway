package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Application;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response.ApplicationDto;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface ApplicationTransform {
    @Named("toCreateCacheApplication")
    @Mapping( target = "applicationId", source = "request.applicationId")
    @Mapping( target = "name", source = "request.name")
    @Mapping( target = "description", source = "request.description")
    @Mapping(target = "desktop", source = "request.desktop")
    @Mapping(target = "mobile", source = "request.mobile")
    @Mapping(target = "typeInternet", source = "request.typeInternet")
    @Mapping( target = "createdBy", source = "request.createdBy")
    @Mapping( target = "modifiedBy", source = "request.modifiedBy")
    @Mapping( target = "createdAt", source = "request.createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping( target = "modifiedAt", source = "request.modifiedAt", qualifiedByName = "stringToLocalDateTime")
    Application toCreateCacheApplication(ApplicationDto request);
}
