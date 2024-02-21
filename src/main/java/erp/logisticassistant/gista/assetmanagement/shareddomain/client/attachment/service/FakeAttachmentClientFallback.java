package erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.service;

import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.request.RequestGistaAttachmentUpdate;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response.*;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class FakeAttachmentClientFallback implements AttachmentClient{
    @Override
    public ResponseEntity<GlobalResponse<ResponseAttachmentClient>> createAttachment(Boolean local,
                                                                                     MultipartFile file,
                                                                                     String documentType,
                                                                                     String description,
                                                                                     String transactionType,
                                                                                     String transactionIde,
                                                                                     String checksum,
                                                                                     String createdBy) {
        ResponseAttachmentClient fakeResponse = createFakeResponse(file ,documentType, description,
                transactionType,checksum,createdBy);
        log.info("Trigger FAKE");
        GlobalResponse<ResponseAttachmentClient> response = new GlobalResponse<>(
                LocalDateTime.now().toString(),
                StatusCode.OK.getHttpStatus().value(),
                StatusCode.OK.getCodeDesc(),
                StatusCode.OK,
                GlobalMessage.Response.Attachment.SUCCESS_UPLOAD_ATTACHMENT,
                fakeResponse
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GlobalResponse<Object>> updateDetailAttachment(String documentDetailId, RequestGistaAttachmentUpdate gistaUpdateAttachment) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadDocumentAttachment(String documentNo) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteDocumentAttachment(String documentNo) {
        return null;
    }

    private ResponseAttachmentClient createFakeResponse(MultipartFile file, String documentType, String description, String transactionType, String checksum, String createdBy) {
        ResponseAttachmentClient fakeResponse = new ResponseAttachmentClient();
        fakeResponse.setDocumentNo(generateDocumentNo());
        fakeResponse.setDocumentType(createFakeDocumentType(documentType));
        fakeResponse.setDetail(createFakeAttachmentDetail(transactionType));
        fakeResponse.setFileName(file.getOriginalFilename());
        fakeResponse.setFileType(file.getContentType());
        fakeResponse.setDescription(description);
        return fakeResponse;
    }

    private ResponseAttachmentDocumentType createFakeDocumentType(String documentTypeId) {
        ResponseAttachmentDocumentType attachmentDocumentType = new ResponseAttachmentDocumentType();
        attachmentDocumentType.setDocumentTypeId(documentTypeId);
        return attachmentDocumentType;
    }

    private ResponseAttachmentDocumentDetailResponse createFakeAttachmentDetail(String transactionType) {
        ResponseAttachmentDocumentDetailResponse attachmentDetail = new ResponseAttachmentDocumentDetailResponse();
        attachmentDetail.setTransactionType(createFakeTransactionType(transactionType));
        attachmentDetail.setTransactionTypeOrigin(createFakeTransactionTypeOrigin(transactionType));
        return attachmentDetail;
    }

    private ResponseTransactionType createFakeTransactionType(String transactionTypeId) {
        ResponseTransactionType responseTransactionType = new ResponseTransactionType();
        responseTransactionType.setTransactionTypeId(transactionTypeId);
        return responseTransactionType;
    }

    private ResponseTransactionTypeOrigin createFakeTransactionTypeOrigin(String transactionTypeId) {
        ResponseTransactionTypeOrigin responseTransactionTypeOrigin = new ResponseTransactionTypeOrigin();
        responseTransactionTypeOrigin.setTransactionTypeId(transactionTypeId);
        return responseTransactionTypeOrigin;
    }

    private String generateDocumentNo() {
        return UUID.randomUUID().toString();
    }
}
