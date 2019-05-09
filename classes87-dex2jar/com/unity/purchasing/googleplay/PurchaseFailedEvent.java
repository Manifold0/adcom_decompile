// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import com.unity.purchasing.common.SaneJSONObject;
import com.unity.purchasing.common.PurchaseFailureReason;

public class PurchaseFailedEvent
{
    public static String jsonEncodePurchaseFailure(final String s, final PurchaseFailureReason purchaseFailureReason, final String s2) {
        final SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("productId", (Object)s);
        saneJSONObject.put("reason", (Object)purchaseFailureReason);
        saneJSONObject.put("message", (Object)s2);
        return saneJSONObject.toString();
    }
}
