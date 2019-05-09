// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.request.WebRequestThread;
import com.unity3d.services.core.request.ResolveHostEvent;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.request.ResolveHostError;
import com.unity3d.services.core.request.IResolveHostListener;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Resolve
{
    @WebViewExposed
    public static void resolve(final String s, final String s2, final WebViewCallback webViewCallback) {
        if (WebRequestThread.resolve(s2, new IResolveHostListener() {
            @Override
            public void onFailed(final String s, final ResolveHostError resolveHostError, final String s2) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.FAILED, s, s, resolveHostError.name(), s2);
                }
            }
            
            @Override
            public void onResolve(final String s, final String s2) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.COMPLETE, s, s, s2);
                }
            }
        })) {
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(ResolveHostError.INVALID_HOST, s);
    }
}
