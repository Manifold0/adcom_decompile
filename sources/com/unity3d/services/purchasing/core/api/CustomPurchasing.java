package com.unity3d.services.purchasing.core.api;

import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import com.unity3d.services.purchasing.core.IPurchasingAdapter;
import com.unity3d.services.purchasing.core.IRetrieveProductsListener;
import com.unity3d.services.purchasing.core.ITransactionListener;
import com.unity3d.services.purchasing.core.Product;
import com.unity3d.services.purchasing.core.PurchasingError;
import com.unity3d.services.purchasing.core.PurchasingEvent;
import com.unity3d.services.purchasing.core.TransactionDetails;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import com.unity3d.services.purchasing.core.TransactionErrorDetails;
import com.unity3d.services.purchasing.core.TransactionErrorDetailsUtilities;
import com.unity3d.services.purchasing.core.properties.ClientProperties;
import com.unity3d.services.purchasing.core.webview.WebViewEventCategory;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPurchasing {
    private static IRetrieveProductsListener retrieveProductsListener = new C16371();
    private static ITransactionListener transactionListener = new C16382();

    /* renamed from: com.unity3d.services.purchasing.core.api.CustomPurchasing$1 */
    static class C16371 implements IRetrieveProductsListener {
        C16371() {
        }

        public void onProductsRetrieved(List<Product> availableProducts) {
            WebViewApp app = WebViewApp.getCurrentApp();
            if (app != null) {
                app.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.PRODUCTS_RETRIEVED, CustomPurchasing.getJSONArrayFromProductList(availableProducts));
            }
        }
    }

    /* renamed from: com.unity3d.services.purchasing.core.api.CustomPurchasing$2 */
    static class C16382 implements ITransactionListener {
        C16382() {
        }

        public void onTransactionComplete(TransactionDetails details) {
            WebViewApp app = WebViewApp.getCurrentApp();
            if (app != null) {
                app.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.TRANSACTION_COMPLETE, TransactionDetailsUtilities.getJSONObjectForTransactionDetails(details));
            }
        }

        public void onTransactionError(TransactionErrorDetails details) {
            WebViewApp app = WebViewApp.getCurrentApp();
            if (app != null) {
                app.sendEvent(WebViewEventCategory.CUSTOM_PURCHASING, PurchasingEvent.TRANSACTION_ERROR, TransactionErrorDetailsUtilities.getJSONObjectForTransactionErrorDetails(details));
            }
        }
    }

    static JSONArray getJSONArrayFromProductList(List<Product> products) {
        JSONArray array = new JSONArray();
        for (Product product : products) {
            try {
                array.put(getJSONObjectForProduct(product));
            } catch (JSONException e) {
                DeviceLog.error("Could not generate JSON for product: %s", e.getMessage());
            }
        }
        return array;
    }

    private static JSONObject getJSONObjectForProduct(Product product) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("productId", product.getProductId());
        object.put("localizedPriceString", product.getLocalizedPriceString());
        object.put("localizedTitle", product.getLocalizedTitle());
        object.put("isoCurrencyCode", product.getIsoCurrencyCode());
        object.put("localizedPrice", product.getLocalizedPrice());
        object.put("localizedDescription", product.getLocalizedDescription());
        object.put(ParametersKeys.PRODUCT_TYPE, product.getProductType());
        return object;
    }

    @WebViewExposed
    public static void available(WebViewCallback callback) {
        boolean z = true;
        Object[] objArr = new Object[1];
        if (ClientProperties.getAdapter() == null) {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        callback.invoke(objArr);
    }

    @WebViewExposed
    public static void refreshCatalog(WebViewCallback callback) {
        final IPurchasingAdapter adapter = ClientProperties.getAdapter();
        if (adapter != null) {
            try {
                Utilities.runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.retrieveProducts(CustomPurchasing.retrieveProductsListener);
                    }
                });
                callback.invoke(new Object[0]);
                return;
            } catch (Exception e) {
                callback.error(PurchasingError.RETRIEVE_PRODUCTS_ERROR, e);
                return;
            }
        }
        callback.error(PurchasingError.PURCHASING_ADAPTER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void purchaseItem(final String productID, final JSONObject extras, WebViewCallback callback) {
        final IPurchasingAdapter adapter = ClientProperties.getAdapter();
        if (adapter != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    adapter.onPurchase(productID, CustomPurchasing.transactionListener, JSONUtilities.jsonObjectToMap(extras));
                }
            });
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(PurchasingError.PURCHASING_ADAPTER_NULL, new Object[0]);
    }
}
