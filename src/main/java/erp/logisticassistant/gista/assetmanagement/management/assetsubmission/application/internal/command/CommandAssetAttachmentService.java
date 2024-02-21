package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.external.OutboundAttachmentService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services.AssetAttachmentValidationService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAttachmentSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionAttachmentTransform;
import erp.logisticassistant.gista.assetmanagement.management.attachment.infrastructure.AttachmentRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.request.RequestGistaAttachmentUpdate;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response.ResponseAttachmentClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.service.UserLoginService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandAssetAttachmentService {
    private final OutboundAttachmentService outboundAttachmentService;
    private final AssetAttachmentValidationService attachmentValidationService;
    private final AttachmentRepository attachmentRepository;
    private final AssetSubmissionAttachmentTransform assetSubmissionAttachmentTransform;
    private final UserLoginService userLoginService;
    private final ApplicationProperties applicationProperties;

    @Transactional
    public String uploadAttachmentAssetSubmission(RequestAttachmentSubmission request) {
        String userLogin = userLoginService.getUsername();
        log.info("=== START UPLOAD ASSET ===");
        try {
            log.info("== TRY IN ===");
            log.info("Request Data FrontEnd :{}", request);
            attachmentValidationService.validateChecksum(request.getFile(), request.getChecksum());
            if (Boolean.FALSE.equals(request.getDeletionFlag())) {
                request.setDocumentType(applicationProperties.getAsset().getAttachment().getSubmission().getDocumentType());
                request.setTransactionType(applicationProperties.getAsset().getAttachment().getSubmission().getTransactionType());
                log.info("Request Data To Send :{}", request);
                ResponseAttachmentClient response = saveAttachmentToGista(request , userLogin);
                log.info("Upload Data Response:{}", response);
                saveAttachmentSubmission(response, request.getNomorRequest(), userLogin);
                return response.getFileName();
            }
            return null;
        } catch (Exception e) {
            log.error("Error uploading attachment asset: " + e);
            throw new GlobalException(StatusCode.BAD_REQUEST, "Dokumen yang diupload bermasalah");
        }
    }

    private ResponseAttachmentClient saveAttachmentToGista(RequestAttachmentSubmission request, String userLogin){
        try {
            log.info("TRY Save AttachmentToGista");
            return outboundAttachmentService.upload(request, userLogin).getResult();
        } catch (Exception e) {
            log.error("Error saving attachment to Gista Attachment: " + e);
            throw new GlobalException(StatusCode.BAD_REQUEST, "Dokumen yang di upload bermasalah");
        }
    }

    private void saveAttachmentSubmission(ResponseAttachmentClient response, String nomorRequest, String userLogin) {
        try {
            attachmentRepository.save(assetSubmissionAttachmentTransform.toAttachmentFromResponseAttachmentClient(response, nomorRequest));
            //saveAttachmentGistaTransactionId(response, nomorRequest, userLogin);
        } catch (Exception e) {
            log.error("Error saving attachment submission: " + e);
            throw new GlobalException(StatusCode.BAD_REQUEST, "Dokumen yang di simpan bermasalah");
        }
    }

    private void saveAttachmentGistaTransactionId(ResponseAttachmentClient response, String nomorRequest, String userLogin) {
        try {
            outboundAttachmentService.updateAttachment(response.getDetail().getDocumentDetailId(),
                    RequestGistaAttachmentUpdate.builder()
                            .transactionTypeId(response.getDetail().getTransactionType().getTransactionTypeId())
                            .documentNo(response.getDocumentNo())
                            .transactionTypeOriginId(response.getDetail().getTransactionTypeOrigin().getTransactionTypeId())
                            .transactionId(nomorRequest)
                            .modifiedBy(userLogin)
                            .build()
            );
        } catch (Exception e) {
            log.error("Error saving attachment transaction id to Gista Attachment: " + e);
            throw new GlobalException(StatusCode.BAD_REQUEST, "Dokumen yang di upload bermasalah");
        }
    }

    public byte[] downloadAttachmentAssetSubmission(String documentNo){
        return outboundAttachmentService.download(documentNo);
    }

    @Transactional
    public String deleteAttachmentAssetSubmission(String documentNo) {
        //deleteAttachmentAssetSubmissionGista(documentNo);
        deleteAttachmentAssetSubmissionDb(documentNo);
        return documentNo;
    }

    private void deleteAttachmentAssetSubmissionGista(String documentNo){
        outboundAttachmentService.delete(documentNo);
    }

    private void deleteAttachmentAssetSubmissionDb(String documentNo){
        attachmentRepository.deleteById(documentNo);
    }
}
