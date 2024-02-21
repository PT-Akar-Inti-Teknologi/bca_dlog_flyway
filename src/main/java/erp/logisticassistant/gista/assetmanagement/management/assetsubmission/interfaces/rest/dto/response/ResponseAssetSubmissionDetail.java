package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAssetSubmissionDetail {
    private Long id;
    private String nomorRequest;
    private String mainAsset;
    private String costCenter;
    private String costCenterDeskripsi;
    private String tipeAt;
    private String tipeAtDeskripsi;
    private String assetClass;
    private String assetClassDeskripsi;
    private Integer nomorBarcode;
    private String namaBarang;
    private String merk;
    private String tipe;
    private String serialNumber;
    private String buildingCode;
    private String buildingFloor;
    private String pic;
    private String tipeSoftware;
    private String tipeSoftwareDeskripsi;
    private String statusAsset;
    private String statusAssetDeskripsi;
    private String nomorPo;
    private String notes;
    private String nomorSertifikat;
    private String spesifikasiStandar;
    private String agreementNo;
    private String kodeDati;
    private String kodeDatiDeskripsi;
    private String kodeKualitas;
    private String kodeKualitasDeskripsi;
    private String hargaPerolehan;
    private String slidTampungan;
    private String coaTampungan;
    private String coaTampunganDeskripsi;
    private String subCoaTampungan;
    private String subCoaTampunganDeskripsi;
    private String cosCenterTampungan;
    private String costCenterTampunganDeskripsi;
    private String productCodeTampungan;
    private String productCodeTampunganDeskripsi;
}
