// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.ads.purchasing.PurchasingError;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.ads.purchasing.IPurchasing;

public class Purchasing
{
    public static IPurchasing purchaseInterface;
    
    static {
        Purchasing.purchaseInterface = null;
    }
    
    @WebViewExposed
    public static void getPromoCatalog(final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onGetProductCatalog();
                }
            }
        });
        if (Purchasing.purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void getPromoVersion(final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onGetPurchasingVersion();
                }
            }
        });
        if (Purchasing.purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void initializePurchasing(final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onInitializePurchasing();
                }
            }
        });
        if (Purchasing.purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void initiatePurchasingCommand(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Purchasing.purchaseInterface != null) {
                    Purchasing.purchaseInterface.onPurchasingCommand(s);
                }
            }
        });
        if (Purchasing.purchaseInterface != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(PurchasingError.PURCHASE_INTERFACE_NULL, new Object[0]);
    }
    
    public static void setPurchasingInterface(final IPurchasing purchaseInterface) {
        Purchasing.purchaseInterface = purchaseInterface;
    }
}
