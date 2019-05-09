// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class AdsProperties
{
    @WebViewExposed
    public static void setShowTimeout(final Integer n, final WebViewCallback webViewCallback) {
        com.unity3d.services.ads.properties.AdsProperties.setShowTimeout(n);
        webViewCallback.invoke(new Object[0]);
    }
}
