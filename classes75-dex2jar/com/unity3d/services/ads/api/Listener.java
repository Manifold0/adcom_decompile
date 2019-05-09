// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Listener
{
    @WebViewExposed
    public static void sendClickEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null && UnityAds.getListener() instanceof IUnityAdsExtendedListener) {
                    ((IUnityAdsExtendedListener)UnityAds.getListener()).onUnityAdsClick(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendErrorEvent(final String s, final String s2, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsError(UnityAds.UnityAdsError.valueOf(s), s2);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendFinishEvent(final String s, final String s2, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsFinish(s, UnityAds.FinishState.valueOf(s2));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendPlacementStateChangedEvent(final String s, final String s2, final String s3, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null && UnityAds.getListener() instanceof IUnityAdsExtendedListener) {
                    ((IUnityAdsExtendedListener)UnityAds.getListener()).onUnityAdsPlacementStateChanged(s, UnityAds.PlacementState.valueOf(s2), UnityAds.PlacementState.valueOf(s3));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendReadyEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsReady(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendStartEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onUnityAdsStart(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
}
