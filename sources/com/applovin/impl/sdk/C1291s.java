package com.applovin.impl.sdk;

/* renamed from: com.applovin.impl.sdk.s */
class C1291s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1290r f2622a;

    C1291s(C1290r c1290r) {
        this.f2622a = c1290r;
    }

    public void run() {
        if (!this.f2622a.f2617a.isForegroundClickInvalidated()) {
            this.f2622a.f2621e.m2121a(this.f2622a.f2618b, this.f2622a.f2619c, this.f2622a.f2620d, this.f2622a.f2617a);
        }
    }
}
