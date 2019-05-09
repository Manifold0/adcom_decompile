package com.unity.purchasing.common;

public class PurchaseFailureDescription {
    public String message;
    public String productId;
    public PurchaseFailureReason reason;
    public String storeSpecificErrorCode;

    public PurchaseFailureDescription(String productId, PurchaseFailureReason reason, String message, String storeSpecificErrorCode) {
        this.productId = productId;
        this.reason = reason;
        this.message = message;
        this.storeSpecificErrorCode = storeSpecificErrorCode;
    }

    public PurchaseFailureDescription(String productId, PurchaseFailureReason reason) {
        this(productId, reason, "", "");
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PurchaseFailureDescription)) {
            return false;
        }
        return equalsAllFields((PurchaseFailureDescription) obj);
    }

    public boolean equalsAllFields(PurchaseFailureDescription other) {
        return this.productId.equals(other.productId) && this.reason == other.reason && this.message.equals(other.message) && this.storeSpecificErrorCode.equals(other.storeSpecificErrorCode);
    }

    public String toString() {
        return "productId: \"" + this.productId + "\" reason: " + this.reason + " message: \"" + this.message + "\" storeSpecificErrorCode: " + this.storeSpecificErrorCode;
    }
}
