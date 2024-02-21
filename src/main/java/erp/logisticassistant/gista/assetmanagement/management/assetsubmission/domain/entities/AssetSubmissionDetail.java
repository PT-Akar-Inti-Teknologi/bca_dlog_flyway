package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities;

import erp.logisticassistant.gista.assetmanagement.management.asset.domain.entities.MasterAssetClass;
import erp.logisticassistant.gista.assetmanagement.management.coa.domain.entities.MasterCoa;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import erp.logisticassistant.gista.assetmanagement.management.kodedati.domain.entities.KodeDati;
import erp.logisticassistant.gista.assetmanagement.management.kodekualitas.domain.entities.KodeKualitas;
import erp.logisticassistant.gista.assetmanagement.management.kodeproduk.domain.entities.MasterKodeProduk;
import erp.logisticassistant.gista.assetmanagement.management.statusasset.domain.entities.StatusAsset;
import erp.logisticassistant.gista.assetmanagement.management.subcoa.domain.entities.MasterSubCoa;
import erp.logisticassistant.gista.assetmanagement.management.tipeat.domain.entities.TipeAt;
import erp.logisticassistant.gista.assetmanagement.management.tipesoftware.domain.entities.TipeSoftware;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "create_asset_manual_detail", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class AssetSubmissionDetail extends AuditEnabledEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false , updatable = false)
    private Long id;
    @Column(name = "nomor_request")
    private String nomorRequest;
    @Column(name = "main_asset")
    private String mainAsset;
    @Column(name = "cost_center")
    private String costCenter;
    @Column(name = "tipe_at")
    private String tipeAt;
    @Column(name = "asset_class")
    private String assetClass;
    @Column(name = "nomor_barcode")
    private Integer nomorBarcode;
    @Column(name = "nama_barang")
    private String namaBarang;
    @Column(name = "merk")
    private String merk;
    @Column(name = "tipe")
    private String tipe;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "building_code")
    private String buildingCode;
    @Column(name = "building_floor")
    private String buildingFloor;
    @Column(name = "pic")
    private String pic;
    @Column(name = "tipe_software")
    private String tipeSoftware;
    @Column(name = "status_aset")
    private String statusAsset;
    @Column(name = "nomor_po")
    private String nomorPo;
    @Column(name = "notes")
    private String notes;
    @Column(name = "nomor_sertifikat")
    private String nomorSertifikat;
    @Column(name = "spesifikasi_standar")
    private String spesifikasiStandar;
    @Column(name = "agreement_no")
    private String agreementNo;
    @Column(name = "kode_dati")
    private String kodeDati;
    @Column(name = "kode_kualitas")
    private String kodeKualitas;
    @Column(name = "harga_perolehan")
    private String hargaPerolehan;
    @Column(name = "slid_tampungan")
    private String slidTampungan;
    @Column(name = "coa_tampungan")
    private String coaTampungan;
    @Column(name = "sub_coa_tampungan")
    private String subCoaTampungan;
    @Column(name = "cost_center_tampungan")
    private String cosCenterTampungan;
    @Column(name = "product_code_tampungan")
    private String productCodeTampungan;
    @Column(name = "search_string")
    private String searchString;
    @ManyToOne
    @NotAudited
    @JoinColumn(name = "tipe_at", referencedColumnName = "kode", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private TipeAt tipeAtEntity;
    @ManyToOne
    @NotAudited
    @JoinColumn(name = "tipe_software", referencedColumnName = "kode", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private TipeSoftware tipeSoftwareEntity;
    @ManyToOne
    @NotAudited
    @JoinColumn(name = "status_aset", referencedColumnName = "kode", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private StatusAsset statusAssetEntity;
    @ManyToOne
    @NotAudited
    @JoinColumn(name = "kode_dati", referencedColumnName = "kode", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private KodeDati kodeDatiEntity;
    @ManyToOne
    @NotAudited
    @JoinColumn(name = "kode_kualitas", referencedColumnName = "kode", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private KodeKualitas kodeKualitasEntity;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "coa_tampungan" , referencedColumnName = "coa_code", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private MasterCoa masterCoa;

    @ManyToOne
    @NotAudited
    @JoinColumns({
            @JoinColumn(name = "coa_tampungan", referencedColumnName = "coa_code", insertable = false, updatable = false),
            @JoinColumn(name = "sub_coa_tampungan", referencedColumnName = "sub_coa_code", insertable = false, updatable = false)
    })
    @EqualsAndHashCode.Exclude
    private MasterSubCoa masterSubCoa;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "product_code_tampungan", referencedColumnName = "product_code", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private MasterKodeProduk masterKodeProduk;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "asset_class", referencedColumnName = "asset_class", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private MasterAssetClass masterAssetClass;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "cost_center", referencedColumnName = "cost_center_id", insertable = false , updatable = false)
    @EqualsAndHashCode.Exclude
    private MasterCostCenter masterCostCenter;

    @ManyToOne
    @NotAudited
    @JoinColumn(name = "cost_center_tampungan", referencedColumnName = "cost_center_id", insertable = false , updatable = false)
    @EqualsAndHashCode.Exclude
    private MasterCostCenter masterCostCenterTampungan;
}
