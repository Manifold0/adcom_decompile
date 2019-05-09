// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.connectivity.ConnectivityMonitor;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Connectivity
{
    @WebViewExposed
    public static void setConnectionMonitoring(final Boolean b, final WebViewCallback webViewCallback) {
        ConnectivityMonitor.setConnectionMonitoring(b);
        webViewCallback.invoke(new Object[0]);
    }
}
