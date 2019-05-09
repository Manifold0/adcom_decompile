package com.applovin.impl.sdk;

import java.util.Map;

class cq implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Map f2241a;
    /* renamed from: b */
    final /* synthetic */ cp f2242b;

    cq(cp cpVar, Map map) {
        this.f2242b = cpVar;
        this.f2241a = map;
    }

    public void run() {
        try {
            this.f2242b.f2239e.m2435a(this.f2241a);
            this.f2242b.f2236b.initialize(this.f2242b.f2239e, this.f2242b.f2237c, this.f2242b.f2237c.getInitializationActivity());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
