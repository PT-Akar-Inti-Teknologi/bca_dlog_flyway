package erp.logisticassistant.gista.assetmanagement.management.branch.domain.entities;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditedMetaData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "branch_midtier" , schema = "masterdata")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@EqualsAndHashCode(callSuper = true)
public class MasterBranchMidTier extends AuditedMetaData {
    @Id
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_initial")
    private String branchInitial;

    @Column(name = "dati2_code")
    private String dati2Code;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "branch_type")
    private String branchType;

    @Column(name = "branch_coordinator")
    private String branchCoordinator;

    @Column(name = "kcu_code")
    private String kcuCode;

    @Column(name = "branch_longitude")
    private String branchLongitude;

    @Column(name = "branch_latitude")
    private String branchLatitude;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_country")
    private String branchCountry;

    @Column(name = "branch_city")
    private String branchCity;

    @Column(name = "branch_province")
    private String branchProvince;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "branch_open_dt")
    private LocalDate branchOpenDt;

    @Column(name = "effective_dt")
    private LocalDate effectiveDt;

    @Column(name = "branch_close_dt")
    private LocalDate branchCloseDt;

    @Column(name = "strt_eff_dt")
    private LocalDate strtEffDt;

    @Column(name = "strt_end_dt")
    private LocalDate strtEndDt;

    @Column(name = "branch_sub_district")
    private String branchSubDistrict;

    @Column(name = "emergency_close_strt_dt")
    private LocalDate emergencyCloseStrtDt;

    @Column(name = "emergency_close_end_dt")
    private LocalDate emergencyCloseEndDt;
    @Type(type = "string-array")
    @Column(name = "path")
    private String[] path;
    @Column(name = "soa_code")
    private String soaCode;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @Column(name = "status")
    private String status;
    @Column(name = "is_kp")
    private Boolean isKp;
}
