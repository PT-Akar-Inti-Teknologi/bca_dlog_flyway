package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.controller;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.command.CommandAssetSubmissionService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.query.QueryAssetSubmissionService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionFilter;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUpload;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUploadDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.query.QueryAssetSubmissionDetailService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.constant.AssetSubmissionSortingConstant;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmissionDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionUpload;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseUploadAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.SummaryUploadAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionDetailTransform;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.PageableUtils;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.SummaryUploadResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.GlobalResponseTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.PageTransform;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.BAD_REQUEST;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/asset-submission")
@Tag(name= GlobalConstant.DOCUMENTATION_GROUP_ASSET)
@SecurityRequirement(name= GlobalConstant.TAG_AUTHENTICATION_HEADER)
public class AssetSubmissionController {
    private final GlobalResponseTransform globalResponseTransform;
    private final PageTransform pageTransform;
    private final QueryAssetSubmissionService queryAssetSubmissionService;
    private final CommandAssetSubmissionService commandAssetSubmissionService;
    private final QueryAssetSubmissionDetailService queryAssetSubmissionDetailService;
    private final AssetSubmissionTransform assetSubmissionTransform;
    private final AssetSubmissionDetailTransform assetSubmissionDetailTransform;
    private final ConvertTransform convertTransform;

    @Operation(summary = GlobalConstant.TAG_ASSET_SUBMISSION_LIST)
    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getListAsset(
            RequestAssetSubmissionFilter filter,
            @RequestParam(value = "page", defaultValue = "${spring.data.rest.default-current-page}") int page,
            @RequestParam(value = "size", defaultValue = "${spring.data.rest.default-page-size}") int size,
            @RequestParam(value = "sort", defaultValue = "${spring.data.rest.default-sorting}") String sort){
        Page<ResponseAssetSubmission> dataAsset = commandAssetSubmissionService.getListAssetSubmission(filter , page, size, sort);
        return ResponseEntity.ok(
                globalResponseTransform.generateResponse(
                        LocalDateTime.now(),
                        StatusCode.OK,
                        pageTransform.toPage(
                                dataAsset.getNumber(),
                                dataAsset.getTotalElements(),
                                dataAsset.getPageable().getOffset(),
                                dataAsset.getSize(),
                                dataAsset.getTotalPages(),
                                assetSubmissionTransform.toAssetSubmissionListResponseDto(dataAsset.getContent())
                        )
                )
        );
    }

    @GetMapping("/{request_no}/header")
    public ResponseEntity<Object> getDetailHeaderAssetSubmission(@PathVariable("request_no") String requestNo){
        GlobalResponse<Object> response = new GlobalResponse<>(
                convertTransform.getTimestamp(LocalDateTime.now()),
                StatusCode.OK.getHttpStatus().value(),
                StatusCode.OK.getCodeDesc(),
                StatusCode.OK,
                StatusCode.OK.getMessage(),
                queryAssetSubmissionService.getAssetSubmissionHeader(requestNo)
        );
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = GlobalConstant.TAG_ASSET_SUBMISSION_DETAIL)
    @GetMapping(
            value = "/{request_no}/detail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailAssetSubmission(@PathVariable("request_no") String requestNo, Pageable pageable){
        Pageable sanitazedPageable = PageableUtils.convertAndFilterSort(pageable , AssetSubmissionSortingConstant.ASSET_DETAIL_SORTABLE_FIELD);
        Page<AssetSubmissionDetail> assetSubmissionDetails = queryAssetSubmissionDetailService.getDetailSubmission(requestNo,sanitazedPageable);
        return ResponseEntity.ok(
                globalResponseTransform.generateResponse(
                        LocalDateTime.now(),
                        StatusCode.OK,
                        pageTransform.toPage(
                                assetSubmissionDetails.getNumber(),
                                assetSubmissionDetails.getTotalElements(),
                                assetSubmissionDetails.getPageable().getOffset(),
                                assetSubmissionDetails.getSize(),
                                assetSubmissionDetails.getTotalPages(),
                                assetSubmissionDetailTransform.toAssetSubmissionDetailsListResponseDto(assetSubmissionDetails.getContent())
                        )
                )
        );
    }

    @Operation(summary = GlobalConstant.TAG_ASSET_SUBMISSION_TEMPLATE_DOWNLOAD)
    @GetMapping(
            value ="/upload/template",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InputStreamResource> processDownloadTemplateUpload() {
        String filename = GlobalConstant.FILENAME_TEMPLATE_UPLOAD_ASSET_SUBMISSION + GlobalConstant.EXTENTION_EXCEL;
        InputStreamResource file = new InputStreamResource(commandAssetSubmissionService.processDownloadTemplateInternal());

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(GlobalConstant.EXTENTION_TYPE_EXCEL))
                .body(file);
    }

    @Operation(summary = GlobalConstant.TAG_ASSET_SUBMISSION_DOWNLOAD)
    @GetMapping(
            value ="/download",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InputStreamResource> processDownload(
           RequestAssetSubmissionFilter filter,
           @RequestParam(value = "sort", defaultValue = "${spring.data.rest.default-sorting}") String sort) {
        String filename = GlobalConstant.FILENAME_EXPORT_ASSET_SUBMISSION + "_" + convertTransform.getDate(LocalDate.now()) + GlobalConstant.EXTENTION_EXCEL;
        InputStreamResource file = new InputStreamResource(commandAssetSubmissionService.processDownload(filter , sort));

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(GlobalConstant.EXTENTION_TYPE_EXCEL))
                .body(file);
    }

    @Operation(summary = GlobalConstant.TAG_ASSET_SUBMISSION_UPLOAD)
    @PostMapping(
            value ="/upload",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GlobalResponse<SummaryUploadResponse<List<ResponseUploadAssetSubmission>>>>  processUploadUserInternal(@RequestBody RequestAssetSubmissionUpload requestList) throws Throwable {
        ResponseAssetSubmissionUpload responseContentUpload = commandAssetSubmissionService.checkContentUploadAssetSubmission(requestList);
//        SummaryUploadAssetSubmission summaryUpload = commandAssetSubmissionService.prosesUploadAssetSubmission(responseContentUpload);
//        if(summaryUpload.getTotalError() >= 1) {
            return ResponseEntity.badRequest().body(new GlobalResponse(
                    convertTransform.getTimestamp(LocalDateTime.now()),
                    BAD_REQUEST.getHttpStatus().value(),
                    BAD_REQUEST.getCodeDesc(),
                    BAD_REQUEST,
                    BAD_REQUEST.getMessage(),
                    null));
//        }else {
//            return ResponseEntity.ok(new GlobalResponse(
//                    convertTransform.getTimestamp(LocalDateTime.now()),
//                    OK.getHttpStatus().value(),
//                    OK.getCodeDesc(),
//                    OK,
//                    OK.getMessage(),
//                    summaryUpload));
//        }
    }

}
