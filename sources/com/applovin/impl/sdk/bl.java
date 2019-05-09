package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdRewardListener;

class bl {
    /* renamed from: a */
    private AppLovinSdkImpl f2148a;
    /* renamed from: b */
    private ax f2149b;
    /* renamed from: c */
    private Activity f2150c;
    /* renamed from: d */
    private AppLovinAdRewardListener f2151d;
    /* renamed from: e */
    private Runnable f2152e;

    private bl() {
    }

    /* renamed from: a */
    bg m2368a() {
        return new bg();
    }

    /* renamed from: a */
    bl m2369a(Activity activity) {
        this.f2150c = activity;
        return this;
    }

    /* renamed from: a */
    bl m2370a(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2148a = appLovinSdkImpl;
        return this;
    }

    /* renamed from: a */
    bl m2371a(ax axVar) {
        this.f2149b = axVar;
        return this;
    }

    /* renamed from: a */
    bl m2372a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f2151d = appLovinAdRewardListener;
        return this;
    }

    /* renamed from: a */
    bl m2373a(Runnable runnable) {
        this.f2152e = runnable;
        return this;
    }
}
