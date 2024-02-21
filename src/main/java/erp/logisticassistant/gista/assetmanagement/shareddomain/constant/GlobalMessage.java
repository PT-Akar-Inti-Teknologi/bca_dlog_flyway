package erp.logisticassistant.gista.assetmanagement.shareddomain.constant;

public class GlobalMessage {

    public static class Annotation{
        public static final String ERROR_EMPTY_DETAIL_ASSET_SUBMISSION = "Detail Pengajuan Asset tidak boleh kosong";
        public static final String ERROR_EMPTY_DETAIL_ASSET_SUBMISSION_ATTACHEMNT = "Pengajuan Asset Attachment tidak boleh kosong";
        public static final String ERROR_EMPTY_DOCUMENT_NO = "No. Dokumen tidak boleh kosong";
        public static final String ERROR_EMPTY_COST_CENTER = "Cost Center Aset tidak boleh kosong";
        public static final String ERROR_EMPTY_TYPE_AT = "Tipe AT tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_TYPE_AT = "Tipe AT harus {max} karakter";
        public static final String ERROR_EMPTY_CLASS = "Class tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_CLASS = "Class harus {max} karakter";
        public static final String ERROR_EMPTY_BARCODE_NUMBER = "No. Barcode tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_BARCODE_NUMBER = "No. Barcode harus {max} karakter";
        public static final String ERROR_EMPTY_ITEM_NAME = "Nama Barang tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_ITEM_NAME = "No. Barcode maksimal {max} karakter";
        public static final String ERROR_EMPTY_BRAND = "Merk tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_BRAND = "No. Barcode maksimal {max} karakter";
        public static final String ERROR_EMPTY_TYPE = "Tipe tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_TYPE = "Tipe maksimal {max} karakter";
        public static final String ERROR_EMPTY_SERIAL_NUMBER = "Serial Number tidak boleh kosong";
        public static final String ERROR_MAX_LENGTH_SERIAL_NUMBER = "Serial Number {max} karakter";
//        public static final String ERROR_MAX_LENGTH_ITEM_NAME = "No. Barcode maksimal {max} karakter";
        public static final String ERROR_EMPTY_BUILDING_CODE = "Kode Gedung tidak boleh kosong";
        public static final String ERROR_EMPTY_BUILDING_FLOOR = "Lantai tidak boleh kosong";
        public static final String ERROR_EMPTY_PIC = "PIC Pengguna tidak boleh kosong";
        public static final String ERROR_EMPTY_SOFTWARE_TYPE = "Tipe Software tidak boleh kosong";
        public static final String ERROR_EMPTY_STATUS_ASSET = "Status Asset tidak boleh kosong";
        public static final String ERROR_EMPTY_NOTES = "Keterangan tidak boleh kosong";
        public static final String ERROR_EMPTY_NOMOR_PO = "Nomor PO tidak boleh kosong";
        public static final String ERROR_EMPTY_DATI_CODE = "Kode Dati tidak boleh kosong";
        public static final String ERROR_EMPTY_QUALITY_CODE = "Kode Kualitas tidak boleh kosong";
        public static final String ERROR_EMPTY_ACQUISITION_COST = "Nilai Perolehan tidak boleh kosong";
        public static final String ERROR_EMPTY_SLID_TEMPORARY = "SLID Tampungan tidak boleh kosong";
        public static final String ERROR_EMPTY_COA_TEMPORARY = "COA Tampungan tidak boleh kosong";
        public static final String ERROR_EMPTY_SUB_COA_TEMPORARY = "Sub Coa Tampungan tidak boleh kosong";
        public static final String ERROR_EMPTY_COST_CENTER_TEMPORARY = "Cost Center Tampungan tidak boleh kosong";
        public static final String ERROR_EMPTY_PRODUCT_CODE_TEMPORARY = "Kode Produk Tampungan tidak boleh kosong";
        public static final String ERROR_SIZE_USER_USERNAME = "Jumlah data Username yang diinput melebihi batas maksimum karakter";
        private Annotation(){}
    }

    public static class Core{
        public static final String ERROR_MEESAGE_KAFKALOG_NOT_FOUND = "Kafka Log not found";
        public static final String ERROR_KAFKA = "Kafka Error";
        public static final String ERROR_MAINTENANCE_MODE = "Layanan ini dalam mode pemeliharaan, silakan hubungi administrator";
        public static final String ERROR_INTERNAL_SERVER = "Terdapat kesalahan pada sistem, Mohon copy trace ID dan screenshoot layar ini kemudian laporkan ke $gista.mail.help untuk ditelusuri lebih lanjut";
        public static final String ERROR_USER_NOT_FOUND = "User tidak terdaftar";

        private Core(){}
    }

    public static class Response {
        private Response() {
        }

        public static class Attachment{
            private Attachment(){}
            public static final String SUCCESS_UPLOAD_ATTACHMENT = "Upload Attachment Berhasil diupload";
            public static final String SUCCESS_DELETE_ATTACHMENT = "Delete Attachment berhasil";
        }

    }

    private GlobalMessage(){}
}
