// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners;

import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.ads.placement.Placement;
import android.app.Activity;
import com.unity3d.services.ads.properties.AdsProperties;

public final class UnityBanners
{
    public static void destroy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!BannerHide.hide()) {
                        sendError("Could not hide banner in time");
                    }
                }
                catch (Exception ex) {
                    sendError(ex.getMessage());
                }
            }
        }).start();
    }
    
    public static IUnityBannerListener getBannerListener() {
        return AdsProperties.getBannerListener();
    }
    
    public static void loadBanner(final Activity activity) {
        loadBanner(activity, Placement.getDefaultBannerPlacement());
    }
    
    public static void loadBanner(final Activity activity, final String s) {
        DeviceLog.entered();
        if (!UnityAds.isSupported()) {
            sendError("Unity Ads is not supported on this device.");
        }
        if (!UnityAds.isInitialized()) {
            sendError("UnityAds is not initialized.");
            return;
        }
        if (!UnityAds.isReady(s)) {
            sendError("Banner placement " + s + " is not ready");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientProperties.setActivity(activity);
                    if (!BannerShow.show(s)) {
                        sendError("Could not show banner in time");
                    }
                }
                catch (Exception ex) {
                    sendError(ex.getMessage());
                }
            }
        }).start();
    }
    
    private static void sendError(final String s) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final IUnityBannerListener bannerListener = UnityBanners.getBannerListener();
                if (bannerListener != null) {
                    bannerListener.onUnityBannerError(s);
                }
            }
        });
    }
    
    public static void setBannerListener(final IUnityBannerListener bannerListener) {
        AdsProperties.setBannerListener(bannerListener);
    }
}
