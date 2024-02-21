package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RequestAssetSubmissionUploadDetail {

    @JsonProperty("main_asset")
    private String mainAsset;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_COST_CENTER)
    @JsonProperty("cost_center")
    private String costCenter;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_TYPE_AT)
    @Size(max = 6 ,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_TYPE_AT
    )
    @JsonProperty("tipe_at")
    private String tipeAt;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_CLASS)
    @Size(max = 3 ,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_CLASS
    )
    @JsonProperty("asset_class")
    private String assetClass;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_BARCODE_NUMBER)
    @Size(max = 8 ,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_BARCODE_NUMBER
    )
    @JsonProperty("nomor_barcode")
    private String nomorBarcode;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_ITEM_NAME)
    @Size(max = 100 ,min = 1,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_ITEM_NAME
    )
    @JsonProperty("nama_barang")
    private String namaBarang;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_BRAND)
    @Size(max = 100 ,min = 1,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_BRAND
    )
    @JsonProperty("merk")
    private String merk;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_TYPE)
    @Size(max = 100 ,min = 1,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_TYPE
    )
    @JsonProperty("tipe")
    private String tipe;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_SERIAL_NUMBER)
    @Size(max = 100 ,min = 1,
            message = GlobalMessage.Annotation.ERROR_MAX_LENGTH_SERIAL_NUMBER
    )
    @JsonProperty("serial_number")
    private String serialNumber;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_BUILDING_CODE)
    @JsonProperty("building_code")
    private String buildingCode;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_BUILDING_FLOOR)
    @JsonProperty("building_floor")
    private String buildingFloor;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_PIC)
    @JsonProperty("pic")
    private String pic;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_SOFTWARE_TYPE)
    @JsonProperty("tipe_software")
    private String tipeSoftware;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_STATUS_ASSET)
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

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_DATI_CODE)
    @JsonProperty("kode_dati")
    private String kodeDati;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_QUALITY_CODE)
    @JsonProperty("kode_kualitas")
    private String kodeKualitas;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_ACQUISITION_COST)
    @JsonProperty("harga_perolehan")
    private String hargaPerolehan;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_SLID_TEMPORARY)
    @JsonProperty("slid_tampungan")
    private String slidTampungan;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_COA_TEMPORARY)
    @JsonProperty("coa_tampungan")
    private String coaTampungan;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_SUB_COA_TEMPORARY)
    @JsonProperty("sub_coa_tampungan")
    private String subCoaTampungan;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_COST_CENTER_TEMPORARY)
    @JsonProperty("cost_center_tampungan")
    private String costCenterTampungan;

    @NotBlank(message = GlobalMessage.Annotation.ERROR_EMPTY_PRODUCT_CODE_TEMPORARY)
    @JsonProperty("product_code_tampungan")
    private String productCodeTampungan;

}
