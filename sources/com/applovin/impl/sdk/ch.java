package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

final class ch implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdViewEventListener f2208a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2209b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdView f2210c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2211d;

    ch(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        this.f2208a = appLovinAdViewEventListener;
        this.f2209b = appLovinAd;
        this.f2210c = appLovinAdView;
        this.f2211d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2208a.adClosedFullscreen(bv.m2407b(this.f2209b), this.f2210c);
        } catch (Throwable th) {
            this.f2211d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen closed event", th);
        }
    }
}
