package com.unity.purchasing.common;

import org.json.JSONObject;

public class PurchaseFailedEvent {
    public static String jsonEncodePurchaseFailure(String productId, PurchaseFailureReason reason, String message) {
        JSONObject json = new JSONObject();
        try {
            json.put("productId", productId);
            json.put("reason", reason);
            json.put("message", message);
            return json.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
