package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

final class ci implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdViewEventListener f2212a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2213b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdView f2214c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2215d;

    ci(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        this.f2212a = appLovinAdViewEventListener;
        this.f2213b = appLovinAd;
        this.f2214c = appLovinAdView;
        this.f2215d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2212a.adLeftApplication(bv.m2407b(this.f2213b), this.f2214c);
        } catch (Throwable th) {
            this.f2215d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about application leave event", th);
        }
    }
}
