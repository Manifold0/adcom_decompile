package com.unity3d.services.ads.api;

import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.PlacementState;
import com.unity3d.ads.UnityAds.UnityAdsError;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class Listener {
    @WebViewExposed
    public static void sendReadyEvent(final String placementId, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsReady(placementId);
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendStartEvent(final String placementId, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsStart(placementId);
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendFinishEvent(final String placementId, final String result, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsFinish(placementId, FinishState.valueOf(result));
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendClickEvent(final String placementId, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null && (UnityAds.getListener() instanceof IUnityAdsExtendedListener)) {
                    ((IUnityAdsExtendedListener) UnityAds.getListener()).onUnityAdsClick(placementId);
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendPlacementStateChangedEvent(final String placementId, final String oldState, final String newState, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null && (UnityAds.getListener() instanceof IUnityAdsExtendedListener)) {
                    ((IUnityAdsExtendedListener) UnityAds.getListener()).onUnityAdsPlacementStateChanged(placementId, PlacementState.valueOf(oldState), PlacementState.valueOf(newState));
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendErrorEvent(final String error, final String message, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsError(UnityAdsError.valueOf(error), message);
                }
            }
        });
        callback.invoke(new Object[0]);
    }
}
