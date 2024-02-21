package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.controller;


import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.command.CommandAssetAttachmentService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAttachmentSubmission;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/asset-submission")
@RequiredArgsConstructor
@Slf4j
public class AssetSubmissionAttachmentController {
    private final ConvertTransform convertTransform;
    private final CommandAssetAttachmentService assetAttachmentService;

    @PostMapping(value = "/user/attachment/upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@ModelAttribute RequestAttachmentSubmission request) {
        GlobalResponse<?> response = new GlobalResponse<>(
                convertTransform.getTimestamp(LocalDateTime.now()),
                StatusCode.OK.getHttpStatus().value(),
                StatusCode.OK.getCodeDesc(),
                StatusCode.OK,
                GlobalMessage.Response.Attachment.SUCCESS_UPLOAD_ATTACHMENT,
                assetAttachmentService.uploadAttachmentAssetSubmission(request)
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/attachment/download/{document_no}")
    public ResponseEntity<Object> download(@PathVariable("document_no") String documentNo){
        return ResponseEntity.ok().body(assetAttachmentService.downloadAttachmentAssetSubmission(documentNo));
    }
    @DeleteMapping("/user/attachment/{document_no}")
    public ResponseEntity<Object> delete(@PathVariable("document_no") String documentNo){
        GlobalResponse<?> response = new GlobalResponse<>(
                convertTransform.getTimestamp(LocalDateTime.now()),
                StatusCode.OK.getHttpStatus().value(),
                StatusCode.OK.getCodeDesc(),
                StatusCode.OK,
                GlobalMessage.Response.Attachment.SUCCESS_DELETE_ATTACHMENT,
                assetAttachmentService.deleteAttachmentAssetSubmission(documentNo)
        );
        return ResponseEntity.ok(response);
    }
}
