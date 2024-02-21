package erp.logisticassistant.gista.assetmanagement.shareddomain.util;

import erp.logisticassistant.gista.assetmanagement.management.costcenter.application.internal.query.QueryCostCenterService;
import erp.logisticassistant.gista.assetmanagement.management.costcenter.domain.entities.MasterCostCenter;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.User;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserInternal;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.UserInternalRepository;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MappingUtils {
    private final UserRepository userRepository;
    private final UserInternalRepository userInternalRepository;
    private final QueryCostCenterService queryCostCenterService;

    @Named("userSubmittedBy")
    public String userSubmittedBy(String modifiedBy){
        Optional<User> data = userRepository.findByUsername(modifiedBy);
        return data.map(user -> user.getUsername() + " - " + user.getName()).orElse("");
    }


    @Named("userDepartmentName")
    public String userDepartmentName(String username) {
        if (StringUtils.isBlank(username)) {
            return "";
        }

        return userRepository.findByUsername(username)
                .flatMap(user -> userInternalRepository.findByUserUserId(user.getUserId()))
                .map(UserInternal::getSubDepartmentName)
                .orElse("");
    }

    @Named("userGroupName")
    public String userGroupName(String userGroupCode){
        if(StringUtils.isBlank(userGroupCode)){
            return "";
        }
        return Optional.ofNullable(queryCostCenterService.getByCostCenterPrefixOrganizationCodeWithRcc(userGroupCode))
                .map(MasterCostCenter::getDescription)
                .orElse("");
    }

}
