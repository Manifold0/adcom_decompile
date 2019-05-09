package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class di implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdPrecacheListener f2322a;
    /* renamed from: b */
    final /* synthetic */ df f2323b;

    di(df dfVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.f2323b = dfVar;
        this.f2322a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f2323b.m2584a(this.f2322a, appLovinNativeAd, i, true);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.f2323b.m2585a(this.f2322a, appLovinNativeAd, true);
    }
}
