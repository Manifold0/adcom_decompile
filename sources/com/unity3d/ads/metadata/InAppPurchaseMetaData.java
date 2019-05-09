package com.unity3d.ads.metadata;

import android.content.Context;
import com.unity3d.services.core.device.Storage;
import com.unity3d.services.core.device.StorageEvent;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.StorageManager.StorageType;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppPurchaseMetaData extends MetaData {
    public static final String IAP_KEY = "iap";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRODUCT_ID = "productId";
    public static final String KEY_RECEIPT_PURCHASE_DATA = "receiptPurchaseData";
    public static final String KEY_SIGNATURE = "signature";

    public InAppPurchaseMetaData(Context context) {
        super(context);
    }

    public void setProductId(String productId) {
        set("productId", productId);
    }

    public void setPrice(Double price) {
        set("price", price);
    }

    public void setCurrency(String currency) {
        set("currency", currency);
    }

    public void setReceiptPurchaseData(String receiptPurchaseData) {
        set(KEY_RECEIPT_PURCHASE_DATA, receiptPurchaseData);
    }

    public void setSignature(String signature) {
        set(KEY_SIGNATURE, signature);
    }

    public synchronized boolean set(String key, Object value) {
        return setRaw(key, value);
    }

    public void commit() {
        if (StorageManager.init(this._context)) {
            Storage storage = StorageManager.getStorage(StorageType.PUBLIC);
            if (getData() != null && storage != null) {
                Object purchaseObject = storage.get("iap.purchases");
                JSONArray purchases = null;
                if (purchaseObject != null) {
                    try {
                        purchases = (JSONArray) purchaseObject;
                    } catch (Exception e) {
                        DeviceLog.error("Invalid object type for purchases");
                    }
                }
                if (purchases == null) {
                    purchases = new JSONArray();
                }
                JSONObject purchase = getData();
                try {
                    purchase.put("ts", System.currentTimeMillis());
                    purchases.put(purchase);
                    storage.set("iap.purchases", purchases);
                    storage.writeStorage();
                    storage.sendEvent(StorageEvent.SET, storage.get("iap.purchases"));
                    return;
                } catch (JSONException e2) {
                    DeviceLog.error("Error constructing purchase object");
                    return;
                }
            }
            return;
        }
        DeviceLog.error("Unity Ads could not commit metadata due to storage error or the data is null");
    }
}
