package com.facebook.ads.internal.p017t;

import com.google.android.gms.nearby.messages.Strategy;
import com.kongregate.android.internal.browser.C0462b;

/* renamed from: com.facebook.ads.internal.t.k */
public enum C2118k {
    HEIGHT_100(-1, 100, 1),
    HEIGHT_120(-1, C0462b.f362a, 2),
    HEIGHT_300(-1, Strategy.TTL_SECONDS_DEFAULT, 3),
    HEIGHT_400(-1, 400, 4),
    HEIGHT_50(-1, 50, 5);
    
    /* renamed from: f */
    private final int f4893f;
    /* renamed from: g */
    private final int f4894g;
    /* renamed from: h */
    private final int f4895h;

    private C2118k(int i, int i2, int i3) {
        this.f4893f = i;
        this.f4894g = i2;
        this.f4895h = i3;
    }

    /* renamed from: a */
    public int m5381a() {
        return this.f4893f;
    }

    /* renamed from: b */
    public int m5382b() {
        return this.f4894g;
    }

    /* renamed from: c */
    public int m5383c() {
        return this.f4895h;
    }
}
