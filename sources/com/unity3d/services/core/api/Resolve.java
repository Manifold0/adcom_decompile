package com.unity3d.services.core.api;

import com.unity3d.services.core.request.IResolveHostListener;
import com.unity3d.services.core.request.ResolveHostError;
import com.unity3d.services.core.request.ResolveHostEvent;
import com.unity3d.services.core.request.WebRequestThread;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class Resolve {
    @WebViewExposed
    public static void resolve(final String id, String host, WebViewCallback callback) {
        if (WebRequestThread.resolve(host, new IResolveHostListener() {
            public void onResolve(String host, String address) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.COMPLETE, id, host, address);
                }
            }

            public void onFailed(String host, ResolveHostError error, String errorMessage) {
                if (WebViewApp.getCurrentApp() != null) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.FAILED, id, host, error.name(), errorMessage);
                }
            }
        })) {
            callback.invoke(id);
            return;
        }
        callback.error(ResolveHostError.INVALID_HOST, id);
    }
}
