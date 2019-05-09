package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Timer;

class bg {
    /* renamed from: a */
    private final AppLovinSdkImpl f2137a;
    /* renamed from: b */
    private final ax f2138b;
    /* renamed from: c */
    private final Activity f2139c;
    /* renamed from: d */
    private final Runnable f2140d;
    /* renamed from: e */
    private final AppLovinAdRewardListener f2141e;
    /* renamed from: f */
    private final Timer f2142f;

    private bg(bl blVar) {
        this.f2137a = blVar.f2148a;
        this.f2138b = blVar.f2149b;
        this.f2139c = blVar.f2150c;
        this.f2140d = blVar.f2152e;
        this.f2141e = blVar.f2151d;
        this.f2142f = new Timer("IncentivizedAdLauncher");
    }

    /* renamed from: a */
    static bl m2356a() {
        return new bl();
    }

    /* renamed from: a */
    void m2362a(AppLovinAd appLovinAd) {
        this.f2139c.runOnUiThread(new bh(this, appLovinAd));
    }
}
