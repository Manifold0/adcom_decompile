package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class dl implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f2329a;
    /* renamed from: b */
    final /* synthetic */ df f2330b;

    dl(df dfVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2330b = dfVar;
        this.f2329a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f2329a != null) {
            this.f2329a.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        if (this.f2329a != null) {
            this.f2329a.onNativeAdsLoaded(list);
        }
    }
}
