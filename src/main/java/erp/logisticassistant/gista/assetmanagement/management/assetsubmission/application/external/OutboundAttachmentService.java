package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.external;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAttachmentSubmission;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.request.RequestGistaAttachmentUpdate;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response.ResponseAttachmentClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.service.AttachmentClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.service.FakeAttachmentClientFallback;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboundAttachmentService {
    private final AttachmentClient attachmentClient;
    private final FakeAttachmentClientFallback fakeAttachmentClientFallback;
    public GlobalResponse<ResponseAttachmentClient> upload(RequestAttachmentSubmission request, String userLogin){
        return fakeAttachmentClientFallback.createAttachment(
                Boolean.TRUE,
                request.getFile(),
                request.getDocumentType(),
                request.getDescription(),
                request.getTransactionType(),
                request.getTransactionIde(),
                request.getChecksum(),
                userLogin
        ).getBody();
    }
    public void updateAttachment(String documentDetailId, RequestGistaAttachmentUpdate gistaUpdateAttachment){
        attachmentClient.updateDetailAttachment(documentDetailId, gistaUpdateAttachment);
    }

    public byte[] download(String documentNo){
        return attachmentClient.downloadDocumentAttachment(documentNo).getBody();
    }

    public Object delete(String documentNo){
        return attachmentClient.deleteDocumentAttachment(documentNo).getBody();
    }
}
