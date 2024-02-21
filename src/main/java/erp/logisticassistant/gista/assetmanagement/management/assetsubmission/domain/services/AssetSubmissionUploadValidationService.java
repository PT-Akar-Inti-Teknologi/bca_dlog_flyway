package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.external.OutboundAssetSubmissionService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUpload;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUploadDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionUploadDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseUploadAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.BAD_REQUEST;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssetSubmissionUploadValidationService {
    private final AssetSubmissionTransform assetSubmissionTransform;
    private final OutboundAssetSubmissionService outboundAssetSubmissionService;
    public void processValidatedAssetSubmissionUpload(RequestAssetSubmissionUpload requestList, List<ResponseAssetSubmissionUploadDetail> responseAssetSubmission) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.getValidator();
            List<ResponseUploadAssetSubmission> responseUploadAssetSubmission = new ArrayList<>();
            if(requestList.getAssetSubmissionDetails() != null){
                requestList.getAssetSubmissionDetails().forEach(request -> {
                    try {
                        Set<ConstraintViolation<RequestAssetSubmissionUploadDetail>> assetSubmissionViolations = validator.validate(request);
                        if (!assetSubmissionViolations.isEmpty()) {
                            ConstraintViolation<RequestAssetSubmissionUploadDetail> violation = assetSubmissionViolations.stream().findFirst().get();
                            responseAssetSubmission.add(assetSubmissionTransform.toAssetSubmissionDetailUploadResponse(request, true, violation.getMessage()));
                        }else {
                            validationCostCenter(responseUploadAssetSubmission, request.getCostCenter());

                        }
                    } catch (Exception e) {
                        log.error("Error Check Content Asset Submission {} {}", request, e);
                        throw new GlobalException(BAD_REQUEST, e.getMessage());
                    }
                });
            }
        }
    }

    private void validationCostCenter(List<ResponseUploadAssetSubmission> responseUploadAssetSubmission, String costCenter) {
        AtomicBoolean resultCostCenter = new AtomicBoolean(false);
        outboundAssetSubmissionService.setValidated(resultCostCenter,Boolean.FALSE.equals(outboundAssetSubmissionService.isValidCostCenterId(costCenter)));
        if (Boolean.FALSE.equals(resultCostCenter.get())){

        }
    }



}
