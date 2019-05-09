package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* renamed from: com.applovin.impl.sdk.i */
class C1282i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdDisplayListener f2588a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2589b;
    /* renamed from: c */
    final /* synthetic */ C1281h f2590c;

    C1282i(C1281h c1281h, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.f2590c = c1281h;
        this.f2588a = appLovinAdDisplayListener;
        this.f2589b = appLovinAd;
    }

    public void run() {
        this.f2588a.adDisplayed(this.f2589b);
    }
}
