package erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusAsset {
    STATUS_DRAFT("DRAFT", "Draft"),
    STATUS_APPROVED("APPROVED", "Approved"),
    STATUS_AWAITING_APPROVAL("AWAITING_APPROVAL", "Waiting for Approval"),
    STATUS_REJECTED("REJECTED", "Rejected"),
    STATUS_CANCELLED("CANCELLED", "Cancelled");
    private final String type;
    private final String value;
}
