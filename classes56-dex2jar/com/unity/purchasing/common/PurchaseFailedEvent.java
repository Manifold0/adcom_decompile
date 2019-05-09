// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import org.json.JSONObject;

public class PurchaseFailedEvent
{
    public static String jsonEncodePurchaseFailure(String string, final PurchaseFailureReason purchaseFailureReason, final String s) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", (Object)string);
            jsonObject.put("reason", (Object)purchaseFailureReason);
            jsonObject.put("message", (Object)s);
            string = jsonObject.toString();
            return string;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
