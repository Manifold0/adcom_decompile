// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.analytics.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.analytics.interfaces.AnalyticsError;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.analytics.interfaces.IAnalytics;

public class Analytics
{
    public static IAnalytics analyticsInterface;
    
    static {
        Analytics.analyticsInterface = null;
    }
    
    @WebViewExposed
    public static void addExtras(final String s, final WebViewCallback webViewCallback) {
        if (Analytics.analyticsInterface != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Analytics.analyticsInterface.onAddExtras(s);
                }
            });
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AnalyticsError.API_NOT_FOUND, s);
    }
    
    public static void setAnalyticsInterface(final IAnalytics analyticsInterface) {
        Analytics.analyticsInterface = analyticsInterface;
    }
}
