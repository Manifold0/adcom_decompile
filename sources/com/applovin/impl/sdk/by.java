package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;

final class by implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdRewardListener f2171a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2172b;
    /* renamed from: c */
    final /* synthetic */ Map f2173c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2174d;

    by(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map map, AppLovinSdk appLovinSdk) {
        this.f2171a = appLovinAdRewardListener;
        this.f2172b = appLovinAd;
        this.f2173c = map;
        this.f2174d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2171a.userOverQuota(bv.m2407b(this.f2172b), this.f2173c);
        } catch (Throwable th) {
            this.f2174d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about exceeding quota", th);
        }
    }
}
