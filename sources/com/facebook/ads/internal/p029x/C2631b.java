package com.facebook.ads.internal.p029x;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.x.b */
public class C2631b {
    /* renamed from: a */
    private C2632c f6579a;
    /* renamed from: b */
    private float f6580b;
    /* renamed from: c */
    private Map<String, String> f6581c;

    public C2631b(C2632c c2632c) {
        this(c2632c, 0.0f);
    }

    public C2631b(C2632c c2632c, float f) {
        this(c2632c, f, null);
    }

    public C2631b(C2632c c2632c, float f, Map<String, String> map) {
        this.f6579a = c2632c;
        this.f6580b = f;
        if (map != null) {
            this.f6581c = map;
        } else {
            this.f6581c = new HashMap();
        }
    }

    /* renamed from: a */
    public boolean m6776a() {
        return this.f6579a == C2632c.IS_VIEWABLE;
    }

    /* renamed from: b */
    public int m6777b() {
        return this.f6579a.m6780a();
    }

    /* renamed from: c */
    public float m6778c() {
        return this.f6580b;
    }

    /* renamed from: d */
    public Map<String, String> m6779d() {
        return this.f6581c;
    }
}
