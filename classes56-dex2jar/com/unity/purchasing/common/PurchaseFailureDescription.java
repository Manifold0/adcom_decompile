// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

public class PurchaseFailureDescription
{
    public String message;
    public String productId;
    public PurchaseFailureReason reason;
    public String storeSpecificErrorCode;
    
    public PurchaseFailureDescription(final String s, final PurchaseFailureReason purchaseFailureReason) {
        this(s, purchaseFailureReason, "", "");
    }
    
    public PurchaseFailureDescription(final String productId, final PurchaseFailureReason reason, final String message, final String storeSpecificErrorCode) {
        this.productId = productId;
        this.reason = reason;
        this.message = message;
        this.storeSpecificErrorCode = storeSpecificErrorCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof PurchaseFailureDescription && this.equalsAllFields((PurchaseFailureDescription)o);
    }
    
    public boolean equalsAllFields(final PurchaseFailureDescription purchaseFailureDescription) {
        return this.productId.equals(purchaseFailureDescription.productId) && this.reason == purchaseFailureDescription.reason && this.message.equals(purchaseFailureDescription.message) && this.storeSpecificErrorCode.equals(purchaseFailureDescription.storeSpecificErrorCode);
    }
    
    @Override
    public String toString() {
        return "productId: \"" + this.productId + "\" reason: " + this.reason + " message: \"" + this.message + "\" storeSpecificErrorCode: " + this.storeSpecificErrorCode;
    }
}
