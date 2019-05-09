package com.unity.purchasing.common;

import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import com.unity3d.services.purchasing.core.TransactionErrorDetailsUtilities;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnityPurchasing implements IStoreCallback {
    private IUnityCallback bridge;

    public UnityPurchasing(IUnityCallback bridge) {
        this.bridge = bridge;
    }

    public void OnSetupFailed(InitializationFailureReason i) {
        this.bridge.OnSetupFailed(i.toString());
    }

    public void OnProductsRetrieved(List<ProductDescription> products) {
        this.bridge.OnProductsRetrieved(SerialiseProducts(products));
    }

    public void OnPurchaseSucceeded(String storeSpecificId, String receipt, String transactionIdentifier) {
        this.bridge.OnPurchaseSucceeded(storeSpecificId, receipt, transactionIdentifier);
    }

    public void OnPurchaseFailed(PurchaseFailureDescription p) {
        this.bridge.OnPurchaseFailed(SerialisePurchaseFailure(p));
    }

    public static String SerialisePurchaseFailure(PurchaseFailureDescription p) {
        SaneJSONObject result = new SaneJSONObject();
        result.put("message", p.message);
        result.put("productId", p.productId);
        result.put("reason", p.reason.toString());
        result.put(TransactionErrorDetailsUtilities.STORE_SPECIFIC_ERROR_CODE, p.storeSpecificErrorCode);
        return result.toString();
    }

    public static String SerialiseProducts(List<ProductDescription> products) {
        JSONArray result = new JSONArray();
        for (ProductDescription product : products) {
            result.put(SerialiseProduct(product));
        }
        return result.toString();
    }

    private static JSONObject SerialiseProduct(ProductDescription product) {
        SaneJSONObject result = new SaneJSONObject();
        result.put("metadata", SerialiseMetadata(product.metadata));
        result.put(TransactionDetailsUtilities.RECEIPT, product.receipt);
        result.put("storeSpecificId", product.storeSpecificId);
        result.put(TransactionDetailsUtilities.TRANSACTION_ID, product.transactionId);
        return result;
    }

    private static JSONObject SerialiseMetadata(ProductMetadata metadata) {
        SaneJSONObject result = new SaneJSONObject();
        result.put("isoCurrencyCode", metadata.isoCurrencyCode);
        result.put("localizedDescription", metadata.localizedDescription);
        result.put("localizedPriceString", metadata.localizedPriceString);
        result.put("localizedPrice", metadata.localizedPrice == null ? 0.0d : metadata.localizedPrice.doubleValue());
        result.put("localizedTitle", metadata.localizedTitle);
        return result;
    }
}
