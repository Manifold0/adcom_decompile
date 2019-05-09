// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public final class TransactionDetailsUtilities
{
    public static final String CURRENCY = "currency";
    public static final String EXTRAS = "extras";
    public static final String PRICE = "price";
    public static final String PRODUCT_ID = "productId";
    public static final String RECEIPT = "receipt";
    public static final String TRANSACTION_ID = "transactionId";
    
    public static Map<String, Object> getEventDataForTransactionDetails(final TransactionDetails transactionDetails) {
        final HashMap<String, String> hashMap = (HashMap<String, String>)new HashMap<String, Map<String, Object>>();
        hashMap.put("transactionId", (Map<String, Object>)transactionDetails.getTransactionId());
        hashMap.put("productId", (Map<String, Object>)transactionDetails.getProductId());
        hashMap.put("price", (Map<String, Object>)transactionDetails.getPrice());
        hashMap.put("currency", (Map<String, Object>)transactionDetails.getCurrency());
        hashMap.put("receipt", (Map<String, Object>)transactionDetails.getReceipt());
        hashMap.put("extras", transactionDetails.getExtras());
        return (Map<String, Object>)hashMap;
    }
    
    public static JSONObject getJSONObjectForTransactionDetails(final TransactionDetails transactionDetails) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("transactionId", (Object)transactionDetails.getTransactionId());
            jsonObject.put("productId", (Object)transactionDetails.getProductId());
            jsonObject.put("price", (Object)transactionDetails.getPrice());
            jsonObject.put("currency", (Object)transactionDetails.getCurrency());
            jsonObject.put("receipt", (Object)transactionDetails.getReceipt());
            jsonObject.put("extras", (Object)JSONUtilities.mapToJsonObject(transactionDetails.getExtras()));
            return jsonObject;
        }
        catch (Exception ex) {
            DeviceLog.error("Could not generate JSON for transaction details: %s", ex.getMessage());
            return jsonObject;
        }
    }
}
