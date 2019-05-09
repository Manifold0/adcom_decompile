package com.unity3d.services.monetization.core.api;

import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.IUnityMonetizationListener;
import com.unity3d.services.monetization.UnityMonetization.PlacementContentState;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentListenerError;
import com.unity3d.services.monetization.core.placementcontent.PlacementContents;
import com.unity3d.services.monetization.core.properties.ClientProperties;

public class MonetizationListener {
    @WebViewExposed
    public static void isMonetizationEnabled(WebViewCallback callback) {
        callback.invoke(Boolean.valueOf(ClientProperties.isMonetizationEnabled()));
    }

    @WebViewExposed
    public static void sendPlacementContentReady(String placementId, WebViewCallback callback) {
        IUnityMonetizationListener placementContentListener = ClientProperties.getListener();
        if (placementContentListener != null) {
            try {
                placementContentListener.onPlacementContentReady(placementId, PlacementContents.getPlacementContent(placementId));
                callback.invoke(new Object[0]);
                return;
            } catch (Exception e) {
                callback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_ERROR, e);
                return;
            }
        }
        callback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void sendPlacementContentStateChanged(String placementId, String previousState, String newState, WebViewCallback callback) {
        IUnityMonetizationListener placementContentListener = ClientProperties.getListener();
        if (placementContentListener != null) {
            try {
                placementContentListener.onPlacementContentStateChange(placementId, PlacementContents.getPlacementContent(placementId), PlacementContentState.valueOf(previousState), PlacementContentState.valueOf(newState));
                callback.invoke(new Object[0]);
                return;
            } catch (Exception e) {
                callback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_ERROR, e);
                return;
            }
        }
        callback.error(PlacementContentListenerError.PLACEMENTCONTENT_LISTENER_NULL, new Object[0]);
    }
}
