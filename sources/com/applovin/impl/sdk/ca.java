package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;

final class ca implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdRewardListener f2183a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2184b;
    /* renamed from: c */
    final /* synthetic */ int f2185c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2186d;

    ca(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, int i, AppLovinSdk appLovinSdk) {
        this.f2183a = appLovinAdRewardListener;
        this.f2184b = appLovinAd;
        this.f2185c = i;
        this.f2186d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2183a.validationRequestFailed(bv.m2407b(this.f2184b), this.f2185c);
        } catch (Throwable th) {
            this.f2186d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request failing", th);
        }
    }
}
