// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.api;

import android.view.View;
import com.unity3d.services.banners.view.BannerView;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.banners.UnityBanners;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class BannerListener
{
    @WebViewExposed
    public static void sendClickEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerClick(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendErrorEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerError(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendHideEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerHide(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendLoadEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerLoaded(s, (View)BannerView.getInstance());
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendShowEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerShow(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendUnloadEvent(final String s, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UnityBanners.getBannerListener() != null) {
                    UnityBanners.getBannerListener().onUnityBannerUnloaded(s);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
}
