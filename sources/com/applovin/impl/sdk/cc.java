package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;

final class cc implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f2190a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2191b;
    /* renamed from: c */
    final /* synthetic */ AppLovinSdk f2192c;

    cc(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.f2190a = appLovinAdDisplayListener;
        this.f2191b = appLovinAd;
        this.f2192c = appLovinSdk;
    }

    public void run() {
        try {
            this.f2190a.adHidden(bv.m2407b(this.f2191b));
        } catch (Throwable th) {
            this.f2192c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
        }
    }
}
