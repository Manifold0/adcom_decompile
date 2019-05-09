package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

final class cg implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdViewEventListener f2204a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2205b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdView f2206c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2207d;

    cg(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        this.f2204a = appLovinAdViewEventListener;
        this.f2205b = appLovinAd;
        this.f2206c = appLovinAdView;
        this.f2207d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2204a.adOpenedFullscreen(bv.m2407b(this.f2205b), this.f2206c);
        } catch (Throwable th) {
            this.f2207d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen opened event", th);
        }
    }
}
