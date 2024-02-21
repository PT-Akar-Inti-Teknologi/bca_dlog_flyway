package erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.BaseFileSyncResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BaseResponseTransform {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "succeed", source = "succeed")
    @Mapping(target = "failedCause", source = "failedCause")
    BaseFileSyncResponse toFileSyncResponse(String id, Boolean succeed, String failedCause);
}
