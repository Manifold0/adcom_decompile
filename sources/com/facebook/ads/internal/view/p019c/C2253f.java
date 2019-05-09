package com.facebook.ads.internal.view.p019c;

/* renamed from: com.facebook.ads.internal.view.c.f */
public enum C2253f {
    UNSPECIFIED(0),
    PORTRAIT(1),
    LANDSCAPE(2);
    
    /* renamed from: d */
    private int f5292d;

    private C2253f(int i) {
        this.f5292d = i;
    }

    /* renamed from: a */
    public static C2253f m5778a(int i) {
        for (C2253f c2253f : C2253f.values()) {
            if (c2253f.f5292d == i) {
                return c2253f;
            }
        }
        return PORTRAIT;
    }

    /* renamed from: a */
    public int m5779a() {
        return this.f5292d;
    }
}
