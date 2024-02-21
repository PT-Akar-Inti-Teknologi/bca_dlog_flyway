package erp.logisticassistant.gista.assetmanagement.shareddomain.constant;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalConstant {

    public static final String FORMAT_LOG = "Failed to produce message";
    /**
     * Tag Authentication
     **/
    public static final String TAG_AUTHENTICATION_FORMAT = "JWT";
    public static final String TAG_AUTHENTICATION_HEADER = "Authorization";
    public static final String TAG_AUTHENTICATION_TYPE = "bearer";

    /**
     * Role
     */

    public static final String ROLE_KEY_OPR = "isOpr";
    public static final String ROLE_PREFIX_OPR = "_OPR";
    public static final String ROLE_KEY_SPV = "isSpv";
    public static final String ROLE_PREFIX_SPV = "_SPV";
    public static final String ROLE_KEY_KANWIL = "isKanwil";
    public static final String ROLE_PREFIX_KANWIL = "_KANWIL";
    public static final String ROLE_KEY_VIEW= "isView";
    public static final String ROLE_PREFIX_VIEW = "_SUPPORT1,_SUPPORT2,_VIEW";

    /**
     * Documentation
     */
    public static final String DOCUMENTATION_DESCRIPTION = "APIs Documentation";
    public static final String DOCUMENTATION_CONTACT_NAME = "AIT";
    public static final String DOCUMENTATION_CONTACT_EMAIL = "-";
    public static final String DOCUMENTATION_CONTACT_URL = "-";

    /**
     * Format
     **/
    public static final String FORMAT_EMAIL_REPLACE = "\\$gista.mail.help";
    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIMESTAMP_ASSET = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /** Regex Pattern **/
    public static final String REGEX_SPASI = " ";
    public static final String REGEX_FORMAT_USERNAME = "^[A-Za-z0-9+_.-]*$";
    public static final String REGEX_FORMAT_UPPERCASE = "^[A-Z0-9]+$";
    public static final String REGEX_FORMAT_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_FORMAT_DATE = "^\\d{4}\\-(0?0[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String REGEX_FORMAT_DIGIT = "^[0-9]*$";
    public static final String REGEX_FORMAT_ACTION = "^[A|D]$";

    /**
     * PACKAGE
     **/
    public static final String DOCUMENTATION_PACKAGE_KAFKALOG =
            "erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.controller";

    /**
     * Controllers Group Name
     **/
    public static final String DOCUMENTATION_GROUP_ASSET = "AssetSubmission Management";
    public static final String DOCUMENTATION_GROUP_KAFKALOG = "Kafka Log";


    /**
     *   Export Content
     **/
    public static final String EXTENTION_TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String EXTENTION_EXCEL = ".xlsx";
    public static final String FILENAME_TEMPLATE_UPLOAD_ASSET_SUBMISSION = "Gista_Template_Pengajuan_Asset";
    public static final String FILENAME_EXPORT_ASSET_SUBMISSION = "Gista_Pengajuan_Asset";
    public static final String VALUE_MAIN_ASSET = "Main Aset";
    public static final String VALUE_COST_CENTER_ASSET = "Cost Center Aset";
    public static final String VALUE_TYPE_AT = "Tipe AT";
    public static final String VALUE_CLASS = "Class";
    public static final String VALUE_BARCODE_NUMBER = "No. Barcode";
    public static final String VALUE_ITEM_NAME = "Nama Barang";
    public static final String VALUE_BRAND = "Merk";
    public static final String VALUE_TYPE = "Tipe";
    public static final String VALUE_SERIAL_NUMBER = "Serial Number";
    public static final String VALUE_BUILDING_CODE = "Kode Gedung";
    public static final String VALUE_FLOOR = "Lantai";
    public static final String VALUE_PIC_USER = "PIC Pengguna";
    public static final String VALUE_TYPE_SOFTWARE = "Tipe Software";
    public static final String VALUE_STATUS_ASSET = "Status Asset";
    public static final String VALUE_NOTES = "Keterangan";
    public static final String VALUE_PO_NUMBER = "Nomor PO";
    public static final String VALUE_PO_CERTIFICATE = "Nomor Sertifikat";
    public static final String VALUE_STANDAR_SPESIFICATION = "Spesifikasi Standar";
    public static final String VALUE_APPROVAL_NUMBER = "Nomor Persetujuan";
    public static final String VALUE_DATI_CDDE = "Kode Dati";
    public static final String VALUE_QUALITY_CODE = "Kode Kualitas";
    public static final String VALUE_POINT = "Nilai Perolehan";
    public static final String VALUE_SLID_TEMPORARY = "SLID Tampungan";
    public static final String VALUE_COA_TEMPORARY = "COA Tampungan";
    public static final String VALUE_COST_CENTER_TEMPORARY = "Cost Center Tampungan";
    public static final String VALUE_PRODUCT_CODE_TEMPORARY = "Kode Produk Tampungan";
    public static final String VALUE_DOCUMENT_NO = "No. Dokumen";
    public static final String VALUE_TOTAL_ASSET = "Jumlah Asset";
    public static final String VALUE_SUBMITTED_BY = "Diajukan Oleh";
    public static final String VALUE_STATUS = "Status";

    /** File Header **/
    public static final String SHEET_UPLOAD_EXAMPLE = "Contoh Pengisian";
    public static final String ASSET_SUBMISSION = "Pengajuan Asset";
    public static final String SHEET_UPLOAD_ASSET_SUBMISSION = ASSET_SUBMISSION;
    public static final String SHEET_DWONLOAD_ASSET_SUBMISSION = ASSET_SUBMISSION;
    public static final List<String> HEADER_DOWNLOAD_TEMPLATE_DATA_ASSET_SUBMISSION = List.of( VALUE_MAIN_ASSET,  VALUE_COST_CENTER_ASSET, VALUE_TYPE_AT, VALUE_CLASS, VALUE_BARCODE_NUMBER, VALUE_ITEM_NAME, VALUE_BRAND, VALUE_TYPE, VALUE_SERIAL_NUMBER, VALUE_BUILDING_CODE,
            VALUE_FLOOR, VALUE_PIC_USER, VALUE_TYPE_SOFTWARE, VALUE_STATUS_ASSET, VALUE_NOTES, VALUE_PO_NUMBER, VALUE_PO_CERTIFICATE, VALUE_STANDAR_SPESIFICATION, VALUE_APPROVAL_NUMBER, VALUE_DATI_CDDE,
            VALUE_QUALITY_CODE, VALUE_POINT, VALUE_SLID_TEMPORARY, VALUE_COA_TEMPORARY, VALUE_COST_CENTER_TEMPORARY, VALUE_PRODUCT_CODE_TEMPORARY);
    public static final List<String> HEADER_DOWNLOAD_LIST_DATA_ASSET_SUBMISSION = List.of( "No", VALUE_DOCUMENT_NO,  VALUE_TOTAL_ASSET, VALUE_SUBMITTED_BY, VALUE_STATUS);



    /**
     * TAG Controllers
     **/
    public static final String TAG_ASSET_SUBMISSION_LIST = "List AssetSubmission";
    public static final String TAG_ASSET_SUBMISSION_DETAIL = "Detail AssetSubmission";
    public static final String TAG_ASSET_SUBMISSION_TEMPLATE_DOWNLOAD = "Download Template Pengajuan Asset";
    public static final String TAG_ASSET_SUBMISSION_DOWNLOAD = "Download Pengajuan Asset";
    public static final String TAG_ASSET_SUBMISSION_UPLOAD = "Upload Pengajuan Asset";
    public static final String TAG_ASSET_SUBMISSION_STATUS_DRAFT = "DRAFT";
    public static final String TAG_ASSET_SUBMISSION_STATUS_APPROVED = "APPROVED";
    public static final String TAG_ASSET_SUBMISSION_STATUS_AWAITING_APPROVAL = "AWAITING_APPROVAL";
    public static final String TAG_ASSET_SUBMISSION_STATUS_REJECTED = "REJECTED";
    public static final String TAG_ASSET_SUBMISSION_STATUS_ACANCELLED = "CANCELLED";


    private GlobalConstant(){}
}