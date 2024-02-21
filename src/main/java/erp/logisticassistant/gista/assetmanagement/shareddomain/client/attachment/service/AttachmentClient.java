package erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.service;

import erp.logisticassistant.gista.assetmanagement.configuration.feign.FeignErrorDecoder;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.request.RequestGistaAttachmentUpdate;
import erp.logisticassistant.gista.assetmanagement.shareddomain.client.attachment.dto.response.ResponseAttachmentClient;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(name = "attachmentClient", url = "${gista.config.attachment.url}", configuration = FeignErrorDecoder.class)
public interface AttachmentClient {

    @PostMapping(value = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<GlobalResponse<ResponseAttachmentClient>> createAttachment(@RequestPart("local") Boolean local,
                                                                              @RequestPart("file") MultipartFile file,
                                                                              @RequestPart("document_type") String documentType,
                                                                              @RequestPart(value = "description") String description,
                                                                              @RequestPart("transaction_type") String transactionType,
                                                                              @RequestPart(value = "transaction_ide") String transactionIde,
                                                                              @RequestPart("checksum") String checksum,
                                                                              @RequestPart("created_by") String createdBy);
    @PutMapping(value = "/document-detail/{document_detail_id}")
    ResponseEntity<GlobalResponse<Object>> updateDetailAttachment(@PathVariable("document_detail_id") String documentDetailId, @RequestBody RequestGistaAttachmentUpdate gistaUpdateAttachment);
    @GetMapping(value = "/document/file/download/{document_no}")
    ResponseEntity<byte[]> downloadDocumentAttachment(@PathVariable("document_no") String documentNo);
    @DeleteMapping(value = "/document/{document_no}")
    ResponseEntity<Object> deleteDocumentAttachment(@PathVariable("document_no") String documentNo);
}
