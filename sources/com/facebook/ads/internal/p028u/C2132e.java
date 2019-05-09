package com.facebook.ads.internal.p028u;

import com.facebook.ads.internal.p043m.C2046c;

/* renamed from: com.facebook.ads.internal.u.e */
class C2132e {
    /* renamed from: a */
    private final C2131a f4941a;
    /* renamed from: b */
    private final C2046c f4942b;
    /* renamed from: c */
    private final String f4943c;
    /* renamed from: d */
    private final String f4944d;
    /* renamed from: e */
    private final String f4945e;

    /* renamed from: com.facebook.ads.internal.u.e$a */
    enum C2131a {
        UNKNOWN,
        ERROR,
        ADS
    }

    C2132e(C2131a c2131a) {
        this(c2131a, null, null, null, null);
    }

    C2132e(C2131a c2131a, C2046c c2046c, String str, String str2, String str3) {
        this.f4941a = c2131a;
        this.f4942b = c2046c;
        this.f4943c = str;
        this.f4944d = str2;
        this.f4945e = str3;
    }

    /* renamed from: a */
    public C2046c mo5506a() {
        return this.f4942b;
    }

    /* renamed from: b */
    C2131a m5431b() {
        return this.f4941a;
    }

    /* renamed from: c */
    String m5432c() {
        return this.f4943c;
    }

    /* renamed from: d */
    String m5433d() {
        return this.f4944d;
    }

    /* renamed from: e */
    String m5434e() {
        return this.f4945e;
    }
}
