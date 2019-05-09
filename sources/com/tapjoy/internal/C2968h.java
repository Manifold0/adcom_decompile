package com.tapjoy.internal;

/* renamed from: com.tapjoy.internal.h */
public final class C2968h {
    /* renamed from: a */
    public String f8025a;
    /* renamed from: b */
    public String f8026b;
    /* renamed from: c */
    public String f8027c;
    /* renamed from: d */
    public long f8028d;
    /* renamed from: e */
    public int f8029e;
    /* renamed from: f */
    public String f8030f;
    /* renamed from: g */
    public String f8031g;

    public C2968h(String str) {
        bs b = bs.m7244b(str);
        b.mo6193h();
        while (b.mo6195j()) {
            String l = b.mo6197l();
            if ("orderId".equals(l)) {
                this.f8025a = b.mo6198m();
            } else if ("packageName".equals(l)) {
                this.f8026b = b.mo6198m();
            } else if ("productId".equals(l)) {
                this.f8027c = b.mo6198m();
            } else if ("purchaseTime".equals(l)) {
                this.f8028d = b.mo6202q();
            } else if ("purchaseState".equals(l)) {
                this.f8029e = b.mo6203r();
            } else if ("developerPayload".equals(l)) {
                this.f8030f = b.mo6198m();
            } else if ("purchaseToken".equals(l)) {
                this.f8031g = b.mo6198m();
            } else {
                b.mo6204s();
            }
        }
        b.mo6194i();
    }
}
