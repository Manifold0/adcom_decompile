package com.applovin.nativeAds;

public interface AppLovinNativeAdService {
    @Deprecated
    boolean hasPreloadedAdForZoneId(String str);

    void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener);

    void loadNativeAds(int i, String str, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener);

    void precacheResources(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener);

    @Deprecated
    void preloadAdForZoneId(String str);
}
