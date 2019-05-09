// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.properties;

import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.ads.IUnityAdsListener;

public class AdsProperties
{
    private static IUnityAdsListener _listener;
    private static int _showTimeout;
    private static IUnityBannerListener bannerListener;
    
    static {
        AdsProperties._showTimeout = 5000;
    }
    
    public static IUnityBannerListener getBannerListener() {
        return AdsProperties.bannerListener;
    }
    
    public static IUnityAdsListener getListener() {
        return AdsProperties._listener;
    }
    
    public static int getShowTimeout() {
        return AdsProperties._showTimeout;
    }
    
    public static void setBannerListener(final IUnityBannerListener bannerListener) {
        AdsProperties.bannerListener = bannerListener;
    }
    
    public static void setListener(final IUnityAdsListener listener) {
        AdsProperties._listener = listener;
    }
    
    public static void setShowTimeout(final int showTimeout) {
        AdsProperties._showTimeout = showTimeout;
    }
}
