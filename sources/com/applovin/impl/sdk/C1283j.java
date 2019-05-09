package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.sdk.j */
class C1283j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f2591a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2592b;
    /* renamed from: c */
    final /* synthetic */ C1281h f2593c;

    C1283j(C1281h c1281h, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.f2593c = c1281h;
        this.f2591a = appLovinAdDisplayListener;
        this.f2592b = appLovinAd;
    }

    public void run() {
        this.f2591a.adHidden(this.f2592b);
    }
}
