package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

abstract class dx implements Runnable {
    /* renamed from: c */
    final String f2257c;
    /* renamed from: d */
    protected final AppLovinSdkImpl f2258d;
    /* renamed from: e */
    final AppLovinLogger f2259e;
    /* renamed from: f */
    final Context f2260f;
    /* renamed from: g */
    protected boolean f2261g;

    dx(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2258d = appLovinSdkImpl;
        if (str == null) {
            str = getClass().getSimpleName();
        }
        this.f2257c = str;
        this.f2259e = appLovinSdkImpl.getLogger();
        this.f2260f = appLovinSdkImpl.getApplicationContext();
    }

    /* renamed from: a */
    String m2482a() {
        return this.f2257c;
    }
}
