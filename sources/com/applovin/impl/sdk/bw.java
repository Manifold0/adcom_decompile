package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;

final class bw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f2164a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2165b;
    /* renamed from: c */
    final /* synthetic */ AppLovinSdk f2166c;

    bw(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.f2164a = appLovinAdDisplayListener;
        this.f2165b = appLovinAd;
        this.f2166c = appLovinSdk;
    }

    public void run() {
        try {
            this.f2164a.adDisplayed(bv.m2407b(this.f2165b));
        } catch (Throwable th) {
            this.f2166c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
        }
    }
}
