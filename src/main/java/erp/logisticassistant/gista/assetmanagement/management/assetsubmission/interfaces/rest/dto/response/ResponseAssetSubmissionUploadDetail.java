package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ResponseAssetSubmissionUploadDetail {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("main_asset")
    private String mainAsset;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cost_center")
    private String costCenter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tipe_at")
    private String tipeAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("asset_class")
    private String assetClass;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nomor_barcode")
    private String nomorBarcode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nama_barang")
    private String namaBarang;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("merk")
    private String merk;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tipe")
    private String tipe;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("building_code")
    private String buildingCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("building_floor")
    private String buildingFloor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("pic")
    private String pic;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tipe_software")
    private String tipeSoftware;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("status_aset")
    private String statusAset;

    /*OPTIONAL*/
    @JsonProperty("notes")
    private String notes;

    /*OPTIONAL*/
    @JsonProperty("nomor_po")
    private String nomorPo;

    /*OPTIONAL*/
    @JsonProperty("nomor_sertifikat")
    private String nomorSertifikat;

    /*OPTIONAL*/
    @JsonProperty("spesifikasi_standar")
    private String spesifikasiStandar;

    /*OPTIONAL*/
    @JsonProperty("agreement_no")
    private String agreementNo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("kode_dati")
    private String kodeDati;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("kode_kualitas")
    private String kodeKualitas;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("harga_perolehan")
    private String hargaPerolehan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("slid_tampungan")
    private String slidTampungan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("coa_tampungan")
    private String coaTampungan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sub_coa_tampungan")
    private String subCoaTampungan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cost_center_tampungan")
    private String costCenterTampungan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("product_code_tampungan")
    private String productCodeTampungan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    private Boolean error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;
}
