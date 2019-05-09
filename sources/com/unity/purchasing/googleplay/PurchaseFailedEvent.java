package com.unity.purchasing.googleplay;

import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.SaneJSONObject;

public class PurchaseFailedEvent {
    public static String jsonEncodePurchaseFailure(String productId, PurchaseFailureReason reason, String message) {
        SaneJSONObject json = new SaneJSONObject();
        json.put("productId", productId);
        json.put("reason", reason);
        json.put("message", message);
        return json.toString();
    }
}
