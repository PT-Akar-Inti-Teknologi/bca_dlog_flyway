package erp.logisticassistant.gista.assetmanagement.shareddomain.util.base;

import com.google.common.base.CaseFormat;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class BaseSpecification {

    public Pageable pageGenerator(int page, int size) {
        return PageRequest.of(page, size);
    }
    public String generateSort(String sort) {
        if(sort != null) {
            String[] splSort = sort.split(",");
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, splSort[0]);
        }
        return null;
    }

    public Sort.Direction getSortDirection(String sort) {
        String[] splSort = sort.split(",");
        String direction = "ASC";
        if(splSort[1] != null){
            direction = splSort[1];
        }
        if (direction.toUpperCase(Locale.ROOT).equals("ASC")) {
            return Sort.Direction.ASC;
        }else{
            return Sort.Direction.DESC;
        }
    }

    public String generateSortFromArray(String sort) {
        if(sort != null) {
            String[] splSort = sort.split("\\|");
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, splSort[0]);
        }
        return null;
    }

    public Sort.Direction getSortDirectionfromArray(String sort) {
        String[] splSort = sort.split("\\|");
        String direction = "ASC";
        if(splSort.length > 1 && splSort[1] != null){
            direction = splSort[1];
        }

        if (direction.toUpperCase(Locale.ROOT).equals("ASC")) {
            return Sort.Direction.ASC;
        }else{
            return Sort.Direction.DESC;
        }
       
    }

    public List<Order> generateListOrder(List<String> sortList, CriteriaBuilder builder, Root root) {
        List<Order> orderList = new ArrayList<>();
            String sortElement = "";
            for (String sort : sortList) {
                String[] arrSort = sort.split(",");
                for (int i = 0; i < arrSort.length; i++) {
                    if(arrSort[i].toUpperCase(Locale.ROOT).equals("DESC")){
                        orderList.add(builder.desc(root.get(sortElement)));
                    } else if(arrSort[i].toUpperCase(Locale.ROOT).equals("ASC")){
                        orderList.add(builder.asc(root.get(sortElement)));
                    } else {
                        sortElement = generateSort(arrSort[i]);
                    }

                }

            }

        return orderList;
    }

}
