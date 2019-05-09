package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.C1382i;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.chartboost.sdk.impl.j */
class C1444j implements Comparable<C1444j> {
    /* renamed from: a */
    final int f3283a;
    /* renamed from: b */
    final String f3284b;
    /* renamed from: c */
    final String f3285c;
    /* renamed from: d */
    final String f3286d;
    /* renamed from: e */
    final AtomicInteger f3287e;
    /* renamed from: f */
    final AtomicInteger f3288f;
    /* renamed from: g */
    private final C1382i f3289g;
    /* renamed from: h */
    private final AtomicReference<C1437h> f3290h;
    /* renamed from: i */
    private final long f3291i;

    public /* synthetic */ int compareTo(Object obj) {
        return m3589a((C1444j) obj);
    }

    C1444j(C1382i c1382i, int i, String str, String str2, String str3, AtomicInteger atomicInteger, AtomicReference<C1437h> atomicReference, long j, AtomicInteger atomicInteger2) {
        this.f3289g = c1382i;
        this.f3283a = i;
        this.f3284b = str;
        this.f3285c = str2;
        this.f3286d = str3;
        this.f3287e = atomicInteger;
        this.f3290h = atomicReference;
        this.f3291i = j;
        this.f3288f = atomicInteger2;
        atomicInteger.incrementAndGet();
    }

    /* renamed from: a */
    public int m3589a(C1444j c1444j) {
        return this.f3283a - c1444j.f3283a;
    }

    /* renamed from: a */
    void m3590a(Executor executor, boolean z) {
        if (this.f3287e.decrementAndGet() == 0 || !z) {
            C1437h c1437h = (C1437h) this.f3290h.getAndSet(null);
            if (c1437h != null) {
                executor.execute(new C1443i(c1437h, z, (int) TimeUnit.NANOSECONDS.toMillis(this.f3289g.m3159b() - this.f3291i), this.f3288f.get()));
            }
        }
    }
}
