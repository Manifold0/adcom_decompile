package com.facebook.ads.internal.view.p061e.p062a;

import com.facebook.ads.internal.adapters.p030b.C1880l;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.e.a.b */
public class C2316b {
    /* renamed from: a */
    private final int f5515a;
    /* renamed from: b */
    private final int f5516b;
    /* renamed from: c */
    private final C1880l f5517c;

    C2316b(int i, int i2, C1880l c1880l) {
        this.f5515a = i;
        this.f5516b = i2;
        this.f5517c = c1880l;
    }

    /* renamed from: a */
    public Map<String, String> m5971a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("cardind", this.f5515a + "");
        hashMap.put("cardcnt", this.f5516b + "");
        return hashMap;
    }

    /* renamed from: b */
    public int m5972b() {
        return this.f5515a;
    }

    /* renamed from: c */
    public C1880l m5973c() {
        return this.f5517c;
    }
}
