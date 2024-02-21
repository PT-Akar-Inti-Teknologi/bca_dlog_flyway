package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services;

import com.google.common.base.Strings;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.entities.AssetSubmission;
import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.request.RequestAssetSubmissionFilter;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.AssosiateRole;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.User;
import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.UserRoleMapping;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.BaseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class AssetSubmissionSpecification extends BaseSpecification {
    public Specification<AssetSubmission> search(String search){
        if(search == null || search.isBlank()){
            return sortingDefault();
        }
        return querySearchingStringFilter(search)
                .and(sortingDefault());
    }

    public static Specification<AssetSubmission> permissionByRole() {

        return (root, query, builder) -> {
            List<Predicate> predicates = new LinkedList<>();
            Join<AssetSubmission, User> userJoin = root.join("submmitedBy");
            Join<User, UserRoleMapping> userRoleMappingJoin = userJoin.join("user", JoinType.LEFT);
            Join<UserRoleMapping, AssosiateRole> assosiateRoleJoin = userRoleMappingJoin.join("user", JoinType.LEFT);
            var searchByPosition = builder.equal(assosiateRoleJoin.get("applicationRoleName"), "NEWBI_ASSET_OPR_KP");
            predicates.add(builder.or(searchByPosition));

            predicates.add(builder.or());
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Specification<AssetSubmission> sortingDefault() {
        return (root, query, builder) -> {
             ((CriteriaQuery) query).orderBy(builder.desc(root.get("modifiedAt")));
             return query.getRestriction();
        };
    }

    public Specification<AssetSubmission> assetFilter(RequestAssetSubmissionFilter filter){
        return search(filter.getSearch());
    }
    public Specification<AssetSubmission> querySearchingStringFilter(String assetSearch){
        return (root, query, cb) -> cb.like(cb.upper(root.get("searchString")), "%" + Strings.nullToEmpty(assetSearch).toUpperCase() + "%");
    }
    public Specification<AssetSubmission> statustFilter(RequestAssetSubmissionFilter filter){
        return status(filter.getStatus());
    }
    public Specification<AssetSubmission> status(String status){
        if(status == null || status.isBlank()){
            return (root, query, cb) -> cb.conjunction();
        }
        return queryStatusFilter(status);
    }
    public Specification<AssetSubmission> queryStatusFilter(String assetStatus){
        List<String> listStatus = Arrays.asList(assetStatus.split(","));
        return (root, query, cb) -> !Strings.isNullOrEmpty(assetStatus)  ? root.get("status").in(listStatus) : cb.conjunction();
    }
}
