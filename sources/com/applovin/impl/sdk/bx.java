package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;

final class bx implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdRewardListener f2167a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2168b;
    /* renamed from: c */
    final /* synthetic */ Map f2169c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2170d;

    bx(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map map, AppLovinSdk appLovinSdk) {
        this.f2167a = appLovinAdRewardListener;
        this.f2168b = appLovinAd;
        this.f2169c = map;
        this.f2170d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2167a.userRewardVerified(bv.m2407b(this.f2168b), this.f2169c);
        } catch (Throwable th) {
            this.f2170d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about successful reward validation request", th);
        }
    }
}
