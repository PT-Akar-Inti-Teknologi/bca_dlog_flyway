package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.attachment.domain.entities.Attachment;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditEnabledEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NamedNativeQuery(name = "countAssetManualHeader",
        query = "select count(1)" +
                "FROM (" +
                    "SELECT " +
                    "FROM( " +
                    "SELECT camh.nomor_request, " +
                    "camh.jumlah_asset, " +
                    "camh.kode_organisasi, " +
                    "camh.note, " +
                    "iu.username, " +
                    "iu.name, " +
                    "camh.requester_dt, " +
                    "camh.is_processed, " +
                    "camh.status, " +
                    "camh.created_at, " +
                    "umr.application_role_name " +
                    "FROM create_asset_manual_header camh " +
                    "LEFT JOIN idm_users iu on iu.username = camh.created_by " +
                    "LEFT JOIN( " +
                    "SELECT iurm.user_id, " +
                    "iar.application_role_name " +
                    "FROM idm_user_role_mapping iurm " +
                    "LEFT JOIN idm_assosiate_role iar on iar.composite_role_id = iurm.composite_role_id " +
                    "WHERE iurm.enabled is true " +
                    "GROUP BY iurm.user_id, iar.application_role_name " +
                    ") umr on umr.user_id = iu.user_id " +
                    "where(COALESCE(:search, null) is null OR " +
                    "(COALESCE(:search ,'') = '' OR " +
                    "lower(camh.search_string) like CONCAT('%', :search, '%'))) " +
                    "AND (COALESCE(:status, null) IS NULL OR " +
                    "(COALESCE(:status, '') = '') OR " +
                    "camh.status IN (:status)) " +
                    "AND ( " +
                    ":isView or COALESCE(:subDepartmentCode, '') = '' OR " +
                    "SUBSTRING(camh.kode_organisasi, 1, 4) IN :subDepartmentCode " +
                    ") " +
                    "AND " +
                    "(:isView or umr.application_role_name IN (:permissionRoleName)) " +
                    ") assetManualHeaders " +
                    "GROUP BY assetmanualheaders.nomor_request, " +
                    "assetmanualheaders.jumlah_asset, " +
                    "assetmanualheaders.kode_organisasi, " +
                    "assetmanualheaders.note, " +
                    "assetmanualheaders.username, " +
                    "assetmanualheaders.name, " +
                    "assetmanualheaders.requester_dt, " +
                    "assetmanualheaders.is_processed, " +
                    "assetmanualheaders.created_at, " +
                    "assetManualHeaders.status " +
                    "ORDER BY " +
                    "CASE WHEN :sortColumn = 'jumlah_asset' AND :sortDirection = 'asc' THEN assetManualHeaders.jumlah_asset END ASC, " +
                    "CASE WHEN :sortColumn = 'jumlah_asset' AND :sortDirection = 'desc' THEN assetManualHeaders.jumlah_asset END DESC, " +
                    "CASE WHEN :sortColumn = 'created_at' AND :sortDirection = 'desc' THEN assetManualHeaders.created_at END DESC, " +
                    "CASE WHEN :sortColumn = 'created_at' AND :sortDirection = 'desc' THEN assetManualHeaders.created_at END DESC " +
                ") countAssetManualHeader",
        resultSetMapping = "responseCountAssetSubmission")
@NamedNativeQuery(name = "findAssetManualHeader",
        query = "SELECT " +
                "assetManualHeaders.nomor_request as nomorRequest, " +
                "assetManualHeaders.jumlah_asset as jumlahAsset, " +
                "assetManualHeaders.kode_organisasi as userGroupCreatedBy, " +
                "assetManualHeaders.note as note, " +
                "assetManualHeaders.username as requesterByUserName, " +
                "assetManualHeaders.name as requesterByName, " +
                "assetManualHeaders.requester_dt as requesterDt, " +
                "assetManualHeaders.is_processed as isProcessed, " +
                "assetManualHeaders.status as status, " +
                "STRING_AGG(assetManualHeaders.application_role_name, ', ') as application_role_name " +
                "FROM( " +
                "SELECT camh.nomor_request, " +
                "camh.jumlah_asset, " +
                "camh.kode_organisasi, " +
                "camh.note, " +
                "iu.username, " +
                "iu.name, " +
                "camh.requester_dt, " +
                "camh.is_processed, " +
                "camh.status, " +
                "camh.created_at, " +
                "umr.application_role_name " +
                "FROM create_asset_manual_header camh " +
                "LEFT JOIN idm_users iu on iu.username = camh.created_by " +
                "LEFT JOIN( " +
                "SELECT iurm.user_id, " +
                "iar.application_role_name " +
                "FROM idm_user_role_mapping iurm " +
                "LEFT JOIN idm_assosiate_role iar on iar.composite_role_id = iurm.composite_role_id " +
                "WHERE iurm.enabled is true " +
                "GROUP BY iurm.user_id, iar.application_role_name " +
                ") umr on umr.user_id = iu.user_id " +
                "where(COALESCE(:search, null) is null OR " +
                "(COALESCE(:search ,'') = '' OR " +
                "lower(camh.search_string) like CONCAT('%', :search, '%'))) " +
                "AND (COALESCE(:status, null) IS NULL OR " +
                "(COALESCE(:status, '') = '') OR " +
                "camh.status IN (:status)) " +
                "AND ( " +
                ":isView or COALESCE(:subDepartmentCode, '') = '' OR " +
                "SUBSTRING(camh.kode_organisasi, 1, 4) IN :subDepartmentCode " +
                ") " +
                "AND " +
                "(:isView or umr.application_role_name IN (:permissionRoleName)) " +
                ") assetManualHeaders " +
                "GROUP BY assetmanualheaders.nomor_request, " +
                "assetmanualheaders.jumlah_asset, " +
                "assetmanualheaders.kode_organisasi, " +
                "assetmanualheaders.note, " +
                "assetmanualheaders.username, " +
                "assetmanualheaders.name, " +
                "assetmanualheaders.requester_dt, " +
                "assetmanualheaders.is_processed, " +
                "assetmanualheaders.created_at, " +
                "assetManualHeaders.status " +
                "ORDER BY " +
                "CASE WHEN :sortColumn = 'jumlah_asset' AND :sortDirection = 'asc' THEN assetManualHeaders.jumlah_asset END ASC, " +
                "CASE WHEN :sortColumn = 'jumlah_asset' AND :sortDirection = 'desc' THEN assetManualHeaders.jumlah_asset END DESC, " +
                "CASE WHEN :sortColumn = 'created_at' AND :sortDirection = 'desc' THEN assetManualHeaders.created_at END DESC, " +
                "CASE WHEN :sortColumn = 'created_at' AND :sortDirection = 'desc' THEN assetManualHeaders.created_at END DESC "
        ,resultSetMapping = "responseAssetSubmission")
@SqlResultSetMapping(name = "responseCountAssetSubmission",
        columns = {
                @ColumnResult(name = "count", type = Integer.class)
        })
@SqlResultSetMapping(name = "responseAssetSubmission",
        classes = @ConstructorResult(
                targetClass = ResponseAssetSubmission.class,
                columns = {
                        @ColumnResult(name = "nomorRequest", type = String.class),
                        @ColumnResult(name = "jumlahAsset", type = Integer.class),
                        @ColumnResult(name = "note", type = String.class),
                        @ColumnResult(name = "requesterByUserName", type = String.class),
                        @ColumnResult(name = "requesterByName", type = String.class),
                        @ColumnResult(name = "requesterDt", type = String.class),
                        @ColumnResult(name = "isProcessed", type = Boolean.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "userGroupCreatedBy", type = String.class)
                }
        ))


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "create_asset_manual_header", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class AssetSubmission extends AuditEnabledEntity {
    @Id
    @Column(name = "nomor_request",  unique = true)
    private String nomorRequest;
    @Column(name = "kode_organisasi")
    private String kodeOrganisasi;
    @Column(name = "jumlah_asset")
    private Integer jumlahAsset;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private String status;
    @Column(name = "requester_by")
    private String requesterBy;
    @Column(name = "requester_dt")
    private LocalDateTime requesterDt;
    @Column(name = "processed_by")
    private String processedBy;
    @Column(name = "processed_dt")
    private LocalDateTime processedDt;
    @Column(name = "is_processed")
    private Boolean isProcessed;
    @Column(name = "search_string")
    private String searchString;
    @OneToMany(mappedBy = "attachmentSubmissionList", cascade = CascadeType.ALL)
    @NotAudited
    private List<Attachment> assetAttachmentSubmissionList;
}
