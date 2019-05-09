package com.applovin.impl.sdk;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* renamed from: com.applovin.impl.sdk.f */
class C1279f extends LinkedHashMap<String, C1278e> {
    /* renamed from: a */
    final /* synthetic */ C1275c f2490a;

    private C1279f(C1275c c1275c) {
        this.f2490a = c1275c;
    }

    protected boolean removeEldestEntry(Entry<String, C1278e> entry) {
        return size() > ((Integer) this.f2490a.f2179a.get(ea.dK)).intValue();
    }
}
