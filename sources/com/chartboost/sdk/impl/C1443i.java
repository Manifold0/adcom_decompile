package com.chartboost.sdk.impl;

/* renamed from: com.chartboost.sdk.impl.i */
public class C1443i implements Runnable {
    /* renamed from: a */
    public final boolean f3279a;
    /* renamed from: b */
    private final C1437h f3280b;
    /* renamed from: c */
    private final int f3281c;
    /* renamed from: d */
    private final int f3282d;

    C1443i(C1437h c1437h, boolean z, int i, int i2) {
        this.f3280b = c1437h;
        this.f3279a = z;
        this.f3281c = i;
        this.f3282d = i2;
    }

    public void run() {
        this.f3280b.mo4311a(this.f3279a, this.f3281c, this.f3282d);
    }
}
