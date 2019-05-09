package com.tapjoy.internal;

/* renamed from: com.tapjoy.internal.g */
public final class C2935g {
    /* renamed from: a */
    public String f7824a;
    /* renamed from: b */
    public String f7825b;
    /* renamed from: c */
    public String f7826c;
    /* renamed from: d */
    public String f7827d;
    /* renamed from: e */
    public String f7828e;
    /* renamed from: f */
    public String f7829f;
    /* renamed from: g */
    public long f7830g;

    public C2935g(String str) {
        bs b = bs.m7244b(str);
        b.mo6193h();
        while (b.mo6195j()) {
            String l = b.mo6197l();
            if ("productId".equals(l)) {
                this.f7824a = b.mo6198m();
            } else if ("type".equals(l)) {
                this.f7825b = b.mo6198m();
            } else if ("price".equals(l)) {
                this.f7826c = b.mo6198m();
            } else if ("title".equals(l)) {
                this.f7827d = b.mo6198m();
            } else if ("description".equals(l)) {
                this.f7828e = b.mo6198m();
            } else if ("price_currency_code".equals(l)) {
                this.f7829f = b.mo6198m();
            } else if ("price_amount_micros".equals(l)) {
                this.f7830g = b.mo6202q();
            } else {
                b.mo6204s();
            }
        }
        b.mo6194i();
    }
}
