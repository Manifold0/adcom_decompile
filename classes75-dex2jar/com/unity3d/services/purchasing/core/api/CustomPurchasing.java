// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core.api;

import com.unity3d.services.purchasing.core.PurchasingError;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import com.unity3d.services.purchasing.core.IPurchasingAdapter;
import org.json.JSONObject;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONArray;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.purchasing.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.purchasing.core.TransactionErrorDetailsUtilities;
import com.unity3d.services.purchasing.core.TransactionErrorDetails;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import com.unity3d.services.purchasing.core.TransactionDetails;
import com.unity3d.services.purchasing.core.PurchasingEvent;
import com.unity3d.services.purchasing.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.purchasing.core.Product;
import java.util.List;
import com.unity3d.services.purchasing.core.ITransactionListener;
import com.unity3d.services.purchasing.core.IRetrieveProductsListener;

public class CustomPurchasing
{
    private static IRetrieveProductsListener retrieveProductsListener;
    private static ITransactionListener transactionListener;
    
    static {
        CustomPurchasing.retrieveProductsListener = new IRetrieveProductsListener() {
            @Override
            public void onProductsRetrieved(final List<Product> list) {
                final WebViewApp currentApp = WebViewApp.getCurrentApp();
                if (currentApp != null) {
                    currentApp.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.PRODUCTS_RETRIEVED, CustomPurchasing.getJSONArrayFromProductList(list));
                }
            }
        };
        CustomPurchasing.transactionListener = new ITransactionListener() {
            @Override
            public void onTransactionComplete(final TransactionDetails transactionDetails) {
                final WebViewApp currentApp = WebViewApp.getCurrentApp();
                if (currentApp != null) {
                    currentApp.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.TRANSACTION_COMPLETE, TransactionDetailsUtilities.getJSONObjectForTransactionDetails(transactionDetails));
                }
            }
            
            @Override
            public void onTransactionError(final TransactionErrorDetails transactionErrorDetails) {
                final WebViewApp currentApp = WebViewApp.getCurrentApp();
                if (currentApp != null) {
                    currentApp.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.TRANSACTION_ERROR, TransactionErrorDetailsUtilities.getJSONObjectForTransactionErrorDetails(transactionErrorDetails));
                }
            }
        };
    }
    
    @WebViewExposed
    public static void available(final WebViewCallback webViewCallback) {
        boolean b = true;
        if (ClientProperties.getAdapter() == null) {
            b = false;
        }
        webViewCallback.invoke(b);
    }
    
    static JSONArray getJSONArrayFromProductList(List<Product> iterator) {
        final JSONArray jsonArray = new JSONArray();
        iterator = ((List<Product>)iterator).iterator();
        while (iterator.hasNext()) {
            final Product product = iterator.next();
            try {
                jsonArray.put((Object)getJSONObjectForProduct(product));
            }
            catch (JSONException ex) {
                DeviceLog.error("Could not generate JSON for product: %s", ex.getMessage());
            }
        }
        return jsonArray;
    }
    
    private static JSONObject getJSONObjectForProduct(final Product product) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("productId", (Object)product.getProductId());
        jsonObject.put("localizedPriceString", (Object)product.getLocalizedPriceString());
        jsonObject.put("localizedTitle", (Object)product.getLocalizedTitle());
        jsonObject.put("isoCurrencyCode", (Object)product.getIsoCurrencyCode());
        jsonObject.put("localizedPrice", product.getLocalizedPrice());
        jsonObject.put("localizedDescription", (Object)product.getLocalizedDescription());
        jsonObject.put("productType", (Object)product.getProductType());
        return jsonObject;
    }
    
    @WebViewExposed
    public static void purchaseItem(final String s, final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        final IPurchasingAdapter adapter = ClientProperties.getAdapter();
        if (adapter != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.onPurchase(s, CustomPurchasing.transactionListener, JSONUtilities.jsonObjectToMap(jsonObject));
                }
            });
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(PurchasingError.PURCHASING_ADAPTER_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void refreshCatalog(final WebViewCallback webViewCallback) {
        final IPurchasingAdapter adapter = ClientProperties.getAdapter();
        if (adapter != null) {
            try {
                Utilities.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.retrieveProducts(CustomPurchasing.retrieveProductsListener);
                    }
                });
                webViewCallback.invoke(new Object[0]);
                return;
            }
            catch (Exception ex) {
                webViewCallback.error(PurchasingError.RETRIEVE_PRODUCTS_ERROR, ex);
                return;
            }
        }
        webViewCallback.error(PurchasingError.PURCHASING_ADAPTER_NULL, new Object[0]);
    }
}
