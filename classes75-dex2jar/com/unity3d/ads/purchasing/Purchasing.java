// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.purchasing;

import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;

public class Purchasing
{
    public static void dispatchReturnEvent(final int n, final String s) {
        final WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp == null || !currentApp.isWebAppLoaded()) {
            return;
        }
        currentApp.sendEvent(WebViewEventCategory.PURCHASING, UnityAdsPurchasingEvent.values()[n], s);
    }
    
    public static void initialize(final IPurchasing purchasingInterface) {
        com.unity3d.services.ads.api.Purchasing.setPurchasingInterface(purchasingInterface);
    }
    
    public enum UnityAdsPurchasingEvent
    {
        CATALOG, 
        COMMAND, 
        EVENT, 
        INITIALIZATION, 
        VERSION;
    }
}
