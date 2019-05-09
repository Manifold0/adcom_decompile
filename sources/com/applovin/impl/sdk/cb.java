package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;

final class cb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdRewardListener f2187a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2188b;
    /* renamed from: c */
    final /* synthetic */ AppLovinSdk f2189c;

    cb(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.f2187a = appLovinAdRewardListener;
        this.f2188b = appLovinAd;
        this.f2189c = appLovinSdk;
    }

    public void run() {
        try {
            this.f2187a.userDeclinedToViewAd(bv.m2407b(this.f2188b));
        } catch (Throwable th) {
            this.f2189c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about user declining to view ad", th);
        }
    }
}
