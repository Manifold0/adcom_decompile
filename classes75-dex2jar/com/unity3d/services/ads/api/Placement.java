// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Placement
{
    @WebViewExposed
    public static void setDefaultPlacement(final String defaultPlacement, final WebViewCallback webViewCallback) {
        com.unity3d.services.ads.placement.Placement.setDefaultPlacement(defaultPlacement);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setPlacementState(final String s, final String s2, final WebViewCallback webViewCallback) {
        com.unity3d.services.ads.placement.Placement.setPlacementState(s, s2);
        webViewCallback.invoke(new Object[0]);
    }
}
