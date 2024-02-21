package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.management.attachment.domain.entities.Attachment;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response.ResponseAttachmentClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface AssetSubmissionAttachmentTransform {

    @Named("toAttachmentFromResponseAttachmentClient")
    default Attachment toAttachmentFromResponseAttachmentClient(ResponseAttachmentClient response, String nomorRequest){
        Attachment attachment = new Attachment();
        attachment.setDocumentNo(response.getDocumentNo());
        attachment.setDocumentType(response.getDocumentType().getDocumentTypeId());
        attachment.setTransactionType(response.getDetail().getTransactionType().getTransactionTypeId());
        attachment.setTransactionId(nomorRequest);
        attachment.setFileUrl(response.getFilePath());
        attachment.setFileName(response.getFileName());
        attachment.setFileType(response.getFileType());
        attachment.setDescription(response.getDescription());
        return attachment;
    }
}
