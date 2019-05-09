package com.unity3d.services.ads.api;

import com.unity3d.ads.purchasing.IPurchasing;
import com.unity3d.ads.purchasing.PurchasingError;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class Purchasing {
    public static IPurchasing purchaseInterface = null;

    /* renamed from: com.unity3d.services.ads.api.Purchasing$2 */
    static class C15692 implements Runnable {
        C15692() {
        }

        public void run() {
            if (Purchasing.purchaseInterface != null) {
                Purchasing.purchaseInterface.onGetPurchasingVersion();
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.api.Purchasing$3 */
    static class C15703 implements Runnable {
        C15703() {
        }

        public void run() {
            if (Purchasing.purchaseInterface != null) {
                Purchasing.purchaseInterface.onGetProductCatalog();
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.api.Purchasing$4 */
    static class C15714 implements Runnable {
        C15714() {
        }

        public void run() {
            if (Purchasing.purchaseInterface != null) {
                Purchasing.purchaseInterface.onInitializePurchasing();
            }
        }
    }

    public static void setPurchasingInterface(IPurchasing purchasing) {
        purchaseInterface = purchasing;
    }

    @WebViewExposed
    public static void initiatePurchasingCommand(final String eventString, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onPurchasingCommand(eventString);
                }
            }
        });
        if (purchaseInterface != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPromoVersion(WebViewCallback callback) {
        Utilities.runOnUiThread(new C15692());
        if (purchaseInterface != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getPromoCatalog(WebViewCallback callback) {
        Utilities.runOnUiThread(new C15703());
        if (purchaseInterface != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void initializePurchasing(WebViewCallback callback) {
        Utilities.runOnUiThread(new C15714());
        if (purchaseInterface != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
        }
    }
}
