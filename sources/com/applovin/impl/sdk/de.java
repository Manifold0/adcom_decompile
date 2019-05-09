package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;

class de implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ dd f2316a;

    de(dd ddVar) {
        this.f2316a = ddVar;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f2316a.m2546c(C1287n.m3049j(this.f2316a.a), i);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        if (!AppLovinSdkUtils.isValidString(appLovinNativeAd.getVideoUrl())) {
            this.f2316a.m2545c((cj) appLovinNativeAd);
        }
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f2316a.b.mo4178w("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
        this.f2316a.m2545c((cj) appLovinNativeAd);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.f2316a.m2545c((cj) appLovinNativeAd);
    }
}
