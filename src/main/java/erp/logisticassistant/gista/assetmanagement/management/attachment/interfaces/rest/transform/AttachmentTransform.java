package erp.logisticassistant.gista.assetmanagement.management.attachment.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.management.attachment.domain.entities.Attachment;
import erp.logisticassistant.gista.assetmanagement.management.attachment.interfaces.rest.dto.ResponseAttachment;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface AttachmentTransform {

    @Named("toResponseAttachment")
    @Mapping(target = "modifiedAt", source = "modifiedAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimetoStringAsset")
    ResponseAttachment toResponseAttachment(Attachment attachment);

    @Named("toResponseListAttachment")
    @IterableMapping(qualifiedByName = "toResponseAttachment")
    List<ResponseAttachment> toResponseListAttachment(List<Attachment> attachmentTransformList);
}
