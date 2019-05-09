// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.api;

import com.unity3d.services.monetization.UnityMonetization;
import com.unity3d.services.monetization.IUnityMonetizationListener;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentListenerError;
import com.unity3d.services.monetization.core.placementcontent.PlacementContents;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class MonetizationListener
{
    @WebViewExposed
    public static void isMonetizationEnabled(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(ClientProperties.isMonetizationEnabled());
    }
    
    @WebViewExposed
    public static void sendPlacementContentReady(final String s, final WebViewCallback webViewCallback) {
        final IUnityMonetizationListener listener = ClientProperties.getListener();
        if (listener != null) {
            try {
                listener.onPlacementContentReady(s, PlacementContents.getPlacementContent(s));
                webViewCallback.invoke(new Object[0]);
                return;
            }
            catch (Exception ex) {
                webViewCallback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_ERROR, ex);
                return;
            }
        }
        webViewCallback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void sendPlacementContentStateChanged(final String s, final String s2, final String s3, final WebViewCallback webViewCallback) {
        final IUnityMonetizationListener listener = ClientProperties.getListener();
        if (listener != null) {
            try {
                listener.onPlacementContentStateChange(s, PlacementContents.getPlacementContent(s), UnityMonetization.PlacementContentState.valueOf(s2), UnityMonetization.PlacementContentState.valueOf(s3));
                webViewCallback.invoke(new Object[0]);
                return;
            }
            catch (Exception ex) {
                webViewCallback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_ERROR, ex);
                return;
            }
        }
        webViewCallback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_NULL, new Object[0]);
    }
}
