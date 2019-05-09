// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.webplayer;

import android.webkit.JavascriptInterface;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;

public class WebPlayerBridgeInterface
{
    private final String viewId;
    
    public WebPlayerBridgeInterface(final String viewId) {
        this.viewId = viewId;
    }
    
    @JavascriptInterface
    public void handleEvent(final String s) {
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.WEBPLAYER_EVENT, s, this.viewId);
        }
    }
}
