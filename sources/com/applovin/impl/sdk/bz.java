package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;

final class bz implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdRewardListener f2175a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2176b;
    /* renamed from: c */
    final /* synthetic */ Map f2177c;
    /* renamed from: d */
    final /* synthetic */ AppLovinSdk f2178d;

    bz(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map map, AppLovinSdk appLovinSdk) {
        this.f2175a = appLovinAdRewardListener;
        this.f2176b = appLovinAd;
        this.f2177c = map;
        this.f2178d = appLovinSdk;
    }

    public void run() {
        try {
            this.f2175a.userRewardRejected(bv.m2407b(this.f2176b), this.f2177c);
        } catch (Throwable th) {
            this.f2178d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request being rejected", th);
        }
    }
}
