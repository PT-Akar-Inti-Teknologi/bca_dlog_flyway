package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionUploadDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.*;
import erp.logisticassistant.gista.assetmanagement.management.attachment.interfaces.rest.transform.AttachmentTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.MappingUtils;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class, MappingUtils.class, AttachmentTransform.class
})
public interface AssetSubmissionTransform {

    @Named("toAssetResponseDto")
    @Mapping(target = "modifiedAt", source = "modifiedAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "userGroupCreatedBy", source = "userGroupCreatedBy" , qualifiedByName = "userGroupName")
    ResponseAssetSubmission toAssetSubmissionResponseDto(ResponseAssetSubmission assetSubmission);

    @Named("toAssetSubmissionHeaderResponseDto")
    @Mapping(target = "requesterByName", source = "requesterBy", qualifiedByName = "userSubmittedBy")
    @Mapping(target = "requesterDt", source = "requesterDt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "processedDt", source = "processedDt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "processedByName", source = "processedBy")
    @Mapping(target = "modifiedAt", source = "modifiedAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "attachmentSubmissionList", source = "assetAttachmentSubmissionList", qualifiedByName = "toResponseListAttachment")
    @Mapping(target = "nomorRequest", source = "nomorRequest")
    @Mapping(target = "jumlahAsset", source = "jumlahAsset")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "isProcessed", source = "isProcessed")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "requesterDepartmentName", source = "requesterBy", qualifiedByName = "userDepartmentName")
    @Mapping(target = "processedDepartmentName", source = "processedBy", qualifiedByName = "userDepartmentName")
    @Mapping(target = "userGroupName", source = "kodeOrganisasi", qualifiedByName = "userGroupName")
    ResponseAssetSubmissionHeader toAssetSubmissionHeaderResponseDto(AssetSubmission assetSubmission);

    @Named("toAssetSubmissionHeaderRoleResponseDto")
    @Mapping(target = "requesterByName", source = "assetSubmission.requesterBy", qualifiedByName = "userSubmittedBy")
    @Mapping(target = "requesterDt", source = "assetSubmission.requesterDt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "processedDt", source = "assetSubmission.processedDt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "processedByName", source = "assetSubmission.processedBy")
    @Mapping(target = "modifiedAt", source = "assetSubmission.modifiedAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "createdAt", source = "assetSubmission.createdAt", qualifiedByName = "localDateTimetoStringAsset")
    @Mapping(target = "attachmentSubmissionList", source = "assetSubmission.assetAttachmentSubmissionList", qualifiedByName = "toResponseListAttachment")
    @Mapping(target = "nomorRequest", source = "assetSubmission.nomorRequest")
    @Mapping(target = "jumlahAsset", source = "assetSubmission.jumlahAsset")
    @Mapping(target = "note", source = "assetSubmission.note")
    @Mapping(target = "isProcessed", source = "assetSubmission.isProcessed")
    @Mapping(target = "status", source = "assetSubmission.status")
    @Mapping(target = "isApprover", source = "isApprover")
    @Mapping(target = "isRequester", source = "isRequester")
    @Mapping(target = "requesterDepartmentName", source = "assetSubmission.requesterBy", qualifiedByName = "userDepartmentName")
    @Mapping(target = "processedDepartmentName", source = "assetSubmission.processedBy", qualifiedByName = "userDepartmentName")
    @Mapping(target = "userGroupName", source = "assetSubmission.kodeOrganisasi", qualifiedByName = "userGroupName")
    ResponseAssetSubmissionHeader toAssetSubmissionHeaderRoleResponseDto(AssetSubmission assetSubmission, Boolean isApprover, Boolean isRequester);

    @Named("toAssetSubmissionListResponseDto")
    @IterableMapping(qualifiedByName = "toAssetResponseDto")
    List<ResponseAssetSubmission> toAssetSubmissionListResponseDto(List<ResponseAssetSubmission> assetSubmission);

    @Named("toAssetSubmissionDetailUploadResponse")
    @Mapping(target = "error", source = "error")
    @Mapping(target = "message", source = "message")
    ResponseAssetSubmissionUploadDetail toAssetSubmissionDetailUploadResponse(RequestAssetSubmissionUploadDetail data, Boolean error, String message);

    @Named("toResponseContentAssetSubmission")
    default ResponseAssetSubmissionUpload toResponseContentAssetSubmission(List<ResponseAssetSubmissionUploadDetail> assetSubmissionDetail){
        return new ResponseAssetSubmissionUpload(assetSubmissionDetail);
    }

}
