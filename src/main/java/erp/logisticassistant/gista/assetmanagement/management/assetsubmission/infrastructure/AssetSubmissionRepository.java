package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.infrastructure;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetSubmissionRepository extends JpaRepository<AssetSubmission, String>,
        JpaSpecificationExecutor<AssetSubmission> {

    @Query(name = "findAssetManualHeader", nativeQuery = true, countName = "countAssetManualHeader")
    Page<ResponseAssetSubmission> findAssetHeaderList(
            @Param("search") String search,
            @Param("status") List<String> status,
            @Param("subDepartmentCode") List<String> subDepartmentCode,
            @Param("isView") Boolean isView,
            @Param("permissionRoleName") List<String> permissionRoleName,
            @Param("sortColumn") String sortColumn,
            @Param("sortDirection") String sortDirection,
            Pageable pageable);

    @Query(name = "findAssetManualHeader", nativeQuery = true, countName = "countAssetManualHeader")
    List<ResponseAssetSubmission> findAssetHeaderList(
            @Param("search") String search,
            @Param("status") List<String> status,
            @Param("subDepartmentCode") List<String> subDepartmentCode,
            @Param("isView") Boolean isView,
            @Param("permissionRoleName") List<String> permissionRoleName,
            @Param("sortColumn") String sortColumn,
            @Param("sortDirection") String sortDirection);
    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM create_asset_manual_header camh " +
            "LEFT JOIN idm_users iu ON iu.username = camh.created_by " +
            "LEFT JOIN ( " +
                    "   SELECT iurm.user_id, iar.application_role_name " +
                    "   FROM idm_user_role_mapping iurm " +
                    "   LEFT JOIN idm_assosiate_role iar ON iar.composite_role_id = iurm.composite_role_id " +
                    "   WHERE iurm.enabled = true" +
            ") umr ON umr.user_id = iu.user_id " +
            "WHERE camh.nomor_request = :nomorRequest AND SUBSTRING(camh.kode_organisasi, 1,4) IN :subDepartmentCode AND umr.application_role_name IN :permissionRoleName")
    Optional<AssetSubmission> findRolesByPermissionRoleName(@Param("nomorRequest") String nomorRequest , @Param("subDepartmentCode") List<String> subDepartmentCode, @Param("permissionRoleName") List<String> permissionRoleName);
}
