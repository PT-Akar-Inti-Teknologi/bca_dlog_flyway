package erp.logisticassistant.gista.assetmanagement.management.asset.domain.entities;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "asset_class", schema = "masterdata")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class MasterAssetClass extends AuditEnabledEntity {
    @Id
    @Column(name = "asset_class")
    private String assetClassId;
    @Column(name = "description")
    private String description;
    @Column(name = "sub_asset_wajib_satu_costcenter")
    private Boolean costCenter;
    @Column(name = "class_lva")
    private Integer classLva;
    @Column(name = "coa_biaya")
    private String coaBiaya;
    @Column(name = "sub_coa_biaya")
    private String subCoaBiaya;
    @Column(name = "batas_nilai_lva")
    private Double batasNilaiLva;
    @Column(name = "coa_asset")
    private String coaAsset;
    @Column(name = "sub_coa_asset")
    private String subCoaAsset;
    @Column(name = "coa_akumulasi")
    private String coaAkumulasi;
    @Column(name = "sub_coa_akumulasi")
    private String subCoaAkumulasi;
    @Column(name = "coa_laba")
    private String coaLaba;
    @Column(name = "sub_coa_laba")
    private String subCoaLaba;
    @Column(name = "coa_rugi")
    private String coaRugi;
    @Column(name = "sub_coa_rugi")
    private String subCoaRugi;
    @Column(name = "metode_depresiasi_fiscal")
    private String metodeDepresiasiFiscal;
    @Column(name = "metode_depresiasi_commercial")
    private String metodeDepresiasiCommercial;
    @Column(name = "coa_biaya_depresiasi")
    private String coaBiayaDepresiasi;
    @Column(name = "sub_coa_biaya_depresiasi")
    private String subCoaDepresiasi;
    @Column(name = "fiscal_life")
    private Integer fiscalLife;
    @Column(name = "commercial_life")
    private Integer commercialLife;
}
