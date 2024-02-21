package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.command;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.query.QueryAssetSubmissionService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services.AssetSubmissionUploadValidationService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionFilter;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUpload;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionUpload;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionUploadDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.file.generator.DownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandAssetSubmissionService {
    private final DownloadService downloadService;
    private final AssetSubmissionTransform assetSubmissionTransform;
    private final QueryAssetSubmissionService queryAssetSubmissionService;
    private final AssetSubmissionUploadValidationService assetSubmissionUploadValidationService;

    public Page<ResponseAssetSubmission> getListAssetSubmission(RequestAssetSubmissionFilter filter, int page, int size, String sort){
        return queryAssetSubmissionService.getListDataAssetSubmissions(filter, page, size, sort);
    }
    public InputStream processDownloadTemplateInternal() {
        try{
            return downloadService.generateFileTemplateAssetSubmission();
        } catch (IOException e) {
            throw new GlobalException(
                    INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    public InputStream processDownload(RequestAssetSubmissionFilter filter, String sort) {
        try{
            List<ResponseAssetSubmission> data = assetSubmissionTransform.toAssetSubmissionListResponseDto(queryAssetSubmissionService.getListDownloadDataAssetSubmissions(filter, sort));
            return downloadService.generateDownloadFileAssetSubmission(data);
        } catch (IOException e) {
            throw new GlobalException(
                    INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    public ResponseAssetSubmissionUpload checkContentUploadAssetSubmission(RequestAssetSubmissionUpload requestList) {
        List<ResponseAssetSubmissionUploadDetail> responseAssetSubmission = new ArrayList<>();
        assetSubmissionUploadValidationService.processValidatedAssetSubmissionUpload(requestList, responseAssetSubmission);
        return assetSubmissionTransform.toResponseContentAssetSubmission(responseAssetSubmission);
    }


}
