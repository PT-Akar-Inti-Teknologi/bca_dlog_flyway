package erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {
        PageTransform.class, ConvertTransform.class
})
public interface GlobalResponseTransform {

    @Named("generateResponse")
    @Mapping(target = "timestamp"  , source = "dateTime", qualifiedByName = "dateTimeToString")
    @Mapping(target = "code"  , expression = "java(statusCode.getHttpStatus().value())")
    @Mapping(target = "appCode"  , source = "statusCode.codeDesc")
    @Mapping(target = "description"  , source = "statusCode")
    @Mapping(target = "message"  , source = "statusCode.message")
    @Mapping(target = "result"  , source = "result")
    GlobalResponse generateResponse(LocalDateTime dateTime, StatusCode statusCode, Object result);

}
