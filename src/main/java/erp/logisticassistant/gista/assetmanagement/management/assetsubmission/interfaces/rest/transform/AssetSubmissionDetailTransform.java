package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmissionDetail;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionDetail;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ConvertTransform.class
})
public interface AssetSubmissionDetailTransform {

    @Named("toResponseDetail")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomorRequest", source = "nomorRequest")
    @Mapping(target = "mainAsset", source = "mainAsset")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "costCenterDeskripsi", source = "masterCostCenter.description")
    @Mapping(target = "tipeAt", source = "tipeAt")
    @Mapping(target = "tipeAtDeskripsi", source = "tipeAtEntity.deskripsi")
    @Mapping(target = "assetClass", source = "assetClass")
    @Mapping(target = "assetClassDeskripsi", source = "masterAssetClass.description")
    @Mapping(target = "tipeSoftware", source = "tipeSoftware")
    @Mapping(target = "tipeSoftwareDeskripsi", source = "tipeSoftwareEntity.deskripsi")
    @Mapping(target = "statusAsset", source = "statusAsset")
    @Mapping(target = "statusAssetDeskripsi", source = "statusAssetEntity.deskripsi")
    @Mapping(target = "kodeDati", source = "kodeDati")
    @Mapping(target = "kodeDatiDeskripsi", source = "kodeDatiEntity.deskripsi")
    @Mapping(target = "kodeKualitas", source = "kodeKualitas")
    @Mapping(target = "kodeKualitasDeskripsi", source = "kodeKualitasEntity.deskripsi")
    @Mapping(target = "coaTampungan", source = "coaTampungan")
    @Mapping(target = "coaTampunganDeskripsi", source = "masterCoa.description")
    @Mapping(target = "subCoaTampungan", source = "subCoaTampungan")
    @Mapping(target = "subCoaTampunganDeskripsi", source = "masterSubCoa.description")
    @Mapping(target = "cosCenterTampungan", source = "cosCenterTampungan")
    @Mapping(target = "costCenterTampunganDeskripsi", source = "masterCostCenterTampungan.description")
    @Mapping(target = "productCodeTampungan", source = "productCodeTampungan")
    @Mapping(target = "productCodeTampunganDeskripsi", source = "masterKodeProduk.description")
    ResponseAssetSubmissionDetail toResponseDetail(AssetSubmissionDetail assetSubmissionDetail);

    @Named("toAssetSubmissionDetailsListResponseDto")
    @IterableMapping(qualifiedByName = "toResponseDetail")
    List<ResponseAssetSubmissionDetail> toAssetSubmissionDetailsListResponseDto(List<AssetSubmissionDetail> assetSubmission);

}
