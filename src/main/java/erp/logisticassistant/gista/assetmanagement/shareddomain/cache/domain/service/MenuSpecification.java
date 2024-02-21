package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.service;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.Menu;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuSpecification extends BaseSpecification {

    private static final String FIELD_MENUID = "menuId";
    private static final String FIELD_ENDPOINT = "endpoint";
    private static final String FIELD_CREATE = "create";
    private static final String FIELD_READ = "read";
    private static final String FIELD_UPDATE = "update";
    private static final String FIELD_DELETE = "delete";
    private static final String FIELD_APPLICATION = "application";
    private static final String FIELD_TYPEINTERNET = "typeInternet";
    private static final String FIELD_ENABLED = "enabled";

    public Specification<Menu> contentFilter(String path, String method, boolean repeat, Boolean isInternet, List<String> ticketMenu) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicate;
            Expression<String> replacedValue;
            if(repeat){
                replacedValue = builder.function("replace",
                        String.class, root.get(FIELD_ENDPOINT), builder.literal("**"),
                        builder.literal(path.split("/")[path.split("/").length-1]+"/"));
            }else {
                replacedValue = builder.function("replace",
                        String.class, root.get(FIELD_ENDPOINT), builder.literal("**"),
                        builder.literal("**"));
            }
            switch (method) {
                case "POST":
                    predicate = builder.and(
                            builder.like(replacedValue, path +"%"),
                            builder.and(builder.isTrue(root.get(FIELD_CREATE))),
                            builder.and(builder.isTrue(root.get(FIELD_ENABLED)))
                    );
                    predicates.add(predicate);
                    break;
                case "GET":
                    predicate = builder.and(
                            builder.like(replacedValue, path +"%"),
                            builder.and(builder.isTrue(root.get(FIELD_READ))),
                            builder.and(builder.isTrue(root.get(FIELD_ENABLED)))
                    );
                    predicates.add(predicate);
                    break;
                case "PUT":
                    predicate = builder.and(
                            builder.like(replacedValue, path +"%"),
                            builder.and(builder.isTrue(root.get(FIELD_UPDATE))),
                            builder.and(builder.isTrue(root.get(FIELD_ENABLED)))
                    );
                    predicates.add(predicate);
                    break;
                case "DELETE":
                    predicate = builder.and(
                            builder.like(replacedValue, path +"%"),
                            builder.and(builder.isTrue(root.get(FIELD_DELETE))),
                            builder.and(builder.isTrue(root.get(FIELD_ENABLED)))
                    );
                    predicates.add(predicate);
                    break;
                default:
                    break;
            }

            if (Boolean.TRUE.equals(isInternet) && ticketMenu == null){
                predicates.add(builder.equal(root.get(FIELD_APPLICATION).get(FIELD_TYPEINTERNET), true));
            }

            if(ticketMenu != null){
                CriteriaBuilder.In<String> inPredicate = builder.in(root.get(FIELD_MENUID));
                for (String menuId : ticketMenu) {
                    inPredicate.value(menuId);
                }
                predicates.add(inPredicate);
            }

            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[]{})));
            return query.getRestriction();
        };
    }

}
