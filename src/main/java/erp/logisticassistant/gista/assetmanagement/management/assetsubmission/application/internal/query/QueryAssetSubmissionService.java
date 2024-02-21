package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.internal.query;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.application.external.OutboundAssetSubmissionService;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services.AssetSubmissionSpecification;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.infrastructure.AssetSubmissionRepository;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionFilter;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmissionHeader;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.transform.AssetSubmissionTransform;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.application.external.outbound.OutboundUserService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.service.UserLoginService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.ExtractCodeUtils;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode.UNAUTHORIZED;

@AllArgsConstructor
@Service
@Slf4j
public class QueryAssetSubmissionService {
    private final AssetSubmissionRepository assetSubmissionRepository;
    private final OutboundUserService outboundUserService;
    private final AssetSubmissionSpecification assetSubmissionSpecification;
    private final AssetSubmissionTransform assetSubmissionTransform;
    private final UserLoginService userLoginService;
    private final OutboundAssetSubmissionService outboundAssetSubmissionService;


    public Page<ResponseAssetSubmission> getListDataAssetSubmissions(RequestAssetSubmissionFilter filter, int page, int size, String sort) {
        if(page != 0) page -= 1;
        String[] splitSort = sort.split(",");
        Pageable pageable = assetSubmissionSpecification.pageGenerator(page, size);
        List<String> asListStatus = Arrays.asList(filter.getStatus().split(","));
        return assetSubmissionRepository.findAssetHeaderList(
                filter.getSearch().toLowerCase(),
                asListStatus,
                organizationFilter(),
                isView(),
                outboundUserService.getByApplicationRoleByGroup(),
                splitSort[0], splitSort[1], pageable);
    }

    public ResponseAssetSubmissionHeader getAssetSubmissionHeader(String nomorRequest) {
        if (isView()) {
            return assetSubmissionTransform.toAssetSubmissionHeaderResponseDto(getAssetSubmission(nomorRequest));
        }
        return assetSubmissionTransform.toAssetSubmissionHeaderRoleResponseDto(getAssetSubmissionByRoleLevel(nomorRequest, organizationFilter()), outboundUserService.isSpv(), outboundUserService.isOpr());
    }

    public List<ResponseAssetSubmission> getListDownloadDataAssetSubmissions(RequestAssetSubmissionFilter filter, String sort) {
        String[] splitSort = sort.split(",");
        List<String> asListStatus = Arrays.asList(filter.getStatus().split(","));

        return assetSubmissionRepository.findAssetHeaderList(
                filter.getSearch().toLowerCase(),
                asListStatus,
                organizationFilter(),
                isView(),
                outboundUserService.getByApplicationRoleByGroup(),
                splitSort[0], splitSort[1]);
    }

    private AssetSubmission getAssetSubmissionByRoleLevel(String nomorRequest, List<String> userDepartmentCode){
        return assetSubmissionRepository.findRolesByPermissionRoleName(nomorRequest, userDepartmentCode, outboundUserService.getByApplicationRoleByGroup())
                .orElseThrow(()->new GlobalException(UNAUTHORIZED, "UNAUTHORIZED"));
    }

    private AssetSubmission getAssetSubmission(String nomorRequest){
        return assetSubmissionRepository.findById(nomorRequest)
                .orElseThrow(()-> new GlobalException(UNAUTHORIZED , "UNAUTHORIZED"));
    }

    private boolean isView() {
        return outboundUserService.hasRole(GlobalConstant.ROLE_KEY_VIEW);
    }

    private boolean isKanwil() {
        return outboundUserService.hasRole(GlobalConstant.ROLE_KEY_KANWIL);
    }

    private List<String> organizationFilter() {
        List<String> organizationFilter = new ArrayList<>();
        organizationFilter.add(ExtractCodeUtils.extractOrganizationCode(userLoginService.getSubDepartmentCode()));

        if (isKanwil()) {
            organizationFilter.addAll(outboundAssetSubmissionService.getAllBranchRelated(ExtractCodeUtils.extractOrganizationCode(userLoginService.getSubDepartmentCode())));
        }

        return organizationFilter;
    }
}
