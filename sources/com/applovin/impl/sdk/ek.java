package com.applovin.impl.sdk;

import java.util.concurrent.atomic.AtomicReference;

class ek implements af<String> {
    /* renamed from: a */
    final /* synthetic */ AtomicReference f2463a;
    /* renamed from: b */
    final /* synthetic */ String f2464b;
    /* renamed from: c */
    final /* synthetic */ ej f2465c;

    ek(ej ejVar, AtomicReference atomicReference, String str) {
        this.f2465c = ejVar;
        this.f2463a = atomicReference;
        this.f2464b = str;
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.f2465c.e.mo4173e(this.f2465c.c, "Failed to load resource from '" + this.f2464b + "'");
    }

    /* renamed from: a */
    public void m2768a(String str, int i) {
        this.f2463a.set(str);
    }
}
