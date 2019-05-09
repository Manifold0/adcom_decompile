package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import java.util.HashSet;

/* renamed from: com.applovin.impl.sdk.v */
class C1294v {
    /* renamed from: a */
    final Object f2626a;
    /* renamed from: b */
    AppLovinAd f2627b;
    /* renamed from: c */
    long f2628c;
    /* renamed from: d */
    boolean f2629d;
    /* renamed from: e */
    private final Collection<AppLovinAdUpdateListener> f2630e;
    /* renamed from: f */
    private final Collection<AppLovinAdLoadListener> f2631f;

    private C1294v() {
        this.f2626a = new Object();
        this.f2630e = new HashSet();
        this.f2631f = new HashSet();
    }

    public String toString() {
        return "AdLoadState{loadedAd=" + this.f2627b + ", loadedAdExpiration=" + this.f2628c + ", isWaitingForAd=" + this.f2629d + ", updateListeners=" + this.f2630e + ", pendingAdListeners=" + this.f2631f + '}';
    }
}
