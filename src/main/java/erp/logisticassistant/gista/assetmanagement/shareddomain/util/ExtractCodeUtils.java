package erp.logisticassistant.gista.assetmanagement.shareddomain.util;

import java.util.List;
import java.util.stream.Collectors;

public class ExtractCodeUtils {
    private ExtractCodeUtils(){}

    public static String extractCostCenter(String input) {
        return input.substring(0, 2);
    }

    public static String extractOrganizationCode(String input) {
        return input.substring(0, 4);
    }

    public static List<String> extractOrganizationCode(List<String> input){
        return input.stream()
                .filter(str -> str.length() >= 4)
                .map(str -> str.substring(0, 4))
                .collect(Collectors.toList());
    }

    public static String extractRcc(String input) {
        return input.substring(6);
    }
}
