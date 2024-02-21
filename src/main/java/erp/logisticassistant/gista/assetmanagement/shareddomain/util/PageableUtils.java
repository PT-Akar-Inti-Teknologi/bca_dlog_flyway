package erp.logisticassistant.gista.assetmanagement.shareddomain.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

public class PageableUtils {
    private PageableUtils(){}
    public static Sort convertAndFilterSort(Sort sort, Map<String, String> whitelistProperties){
        return Sort.by(sort.stream()
                .filter(order -> whitelistProperties.containsKey(order.getProperty()))
                .map(order -> order.withProperty(whitelistProperties.get(order.getProperty())))
                .collect(Collectors.toList())
        );
    }
    public static Pageable convertAndFilterSort(Pageable pageable, Map<String, String> whitelistProperties){
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), convertAndFilterSort(pageable.getSort(), whitelistProperties));
    }
}
