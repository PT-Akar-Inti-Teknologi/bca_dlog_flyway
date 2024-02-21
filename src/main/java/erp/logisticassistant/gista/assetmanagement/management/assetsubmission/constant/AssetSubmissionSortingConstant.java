package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.constant;

import java.util.Map;

public class AssetSubmissionSortingConstant {
    private AssetSubmissionSortingConstant(){}
    public static final Map<String, String> ASSET_SORTABLE_FIELD = Map.ofEntries(
            Map.entry("jumlah_asset", "jumlahAsset")
    );

    public static final Map<String, String> ASSET_DETAIL_SORTABLE_FIELD = Map.ofEntries(
            Map.entry("id", "id")
    );
}
