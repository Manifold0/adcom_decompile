// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import java.util.Iterator;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONObject;

public class UnityPurchasing implements IStoreCallback
{
    private IUnityCallback bridge;
    
    public UnityPurchasing(final IUnityCallback bridge) {
        this.bridge = bridge;
    }
    
    private static JSONObject SerialiseMetadata(final ProductMetadata productMetadata) {
        final SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("isoCurrencyCode", productMetadata.isoCurrencyCode);
        saneJSONObject.put("localizedDescription", productMetadata.localizedDescription);
        saneJSONObject.put("localizedPriceString", productMetadata.localizedPriceString);
        double doubleValue;
        if (productMetadata.localizedPrice == null) {
            doubleValue = 0.0;
        }
        else {
            doubleValue = productMetadata.localizedPrice.doubleValue();
        }
        saneJSONObject.put("localizedPrice", doubleValue);
        saneJSONObject.put("localizedTitle", productMetadata.localizedTitle);
        return saneJSONObject;
    }
    
    private static JSONObject SerialiseProduct(final ProductDescription productDescription) {
        final SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("metadata", SerialiseMetadata(productDescription.metadata));
        saneJSONObject.put("receipt", productDescription.receipt);
        saneJSONObject.put("storeSpecificId", productDescription.storeSpecificId);
        saneJSONObject.put("transactionId", productDescription.transactionId);
        return saneJSONObject;
    }
    
    public static String SerialiseProducts(final List<ProductDescription> list) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<ProductDescription> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)SerialiseProduct(iterator.next()));
        }
        return jsonArray.toString();
    }
    
    public static String SerialisePurchaseFailure(final PurchaseFailureDescription purchaseFailureDescription) {
        final SaneJSONObject saneJSONObject = new SaneJSONObject();
        saneJSONObject.put("message", purchaseFailureDescription.message);
        saneJSONObject.put("productId", purchaseFailureDescription.productId);
        saneJSONObject.put("reason", purchaseFailureDescription.reason.toString());
        saneJSONObject.put("storeSpecificErrorCode", purchaseFailureDescription.storeSpecificErrorCode);
        return saneJSONObject.toString();
    }
    
    @Override
    public void OnProductsRetrieved(final List<ProductDescription> list) {
        this.bridge.OnProductsRetrieved(SerialiseProducts(list));
    }
    
    @Override
    public void OnPurchaseFailed(final PurchaseFailureDescription purchaseFailureDescription) {
        this.bridge.OnPurchaseFailed(SerialisePurchaseFailure(purchaseFailureDescription));
    }
    
    @Override
    public void OnPurchaseSucceeded(final String s, final String s2, final String s3) {
        this.bridge.OnPurchaseSucceeded(s, s2, s3);
    }
    
    @Override
    public void OnSetupFailed(final InitializationFailureReason initializationFailureReason) {
        this.bridge.OnSetupFailed(initializationFailureReason.toString());
    }
}
