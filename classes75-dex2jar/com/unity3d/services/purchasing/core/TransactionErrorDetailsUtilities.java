// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import org.json.JSONObject;

public final class TransactionErrorDetailsUtilities
{
    public static final String EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String EXTRAS = "extras";
    public static final String STORE = "store";
    public static final String STORE_SPECIFIC_ERROR_CODE = "storeSpecificErrorCode";
    public static final String TRANSACTION_ERROR = "transactionError";
    
    public static JSONObject getJSONObjectForTransactionErrorDetails(final TransactionErrorDetails transactionErrorDetails) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("transactionError", (Object)transactionErrorDetails.getTransactionError().toString());
            jsonObject.put("exceptionMessage", (Object)transactionErrorDetails.getExceptionMessage());
            jsonObject.put("store", (Object)transactionErrorDetails.getStore().toString());
            jsonObject.put("storeSpecificErrorCode", (Object)transactionErrorDetails.getStoreSpecificErrorCode());
            jsonObject.put("extras", (Object)JSONUtilities.mapToJsonObject(transactionErrorDetails.getExtras()));
            return jsonObject;
        }
        catch (Exception ex) {
            DeviceLog.error("Could not generate JSON for Transaction Error Details: %s", ex.getMessage());
            return jsonObject;
        }
    }
}
