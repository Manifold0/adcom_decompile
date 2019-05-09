package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.impl.sdk.g */
public class C1280g {
    /* renamed from: a */
    private final C1227q f2547a;
    /* renamed from: b */
    private final long f2548b;
    /* renamed from: c */
    private final C1275c f2549c;
    /* renamed from: d */
    private final aw f2550d;
    /* renamed from: e */
    private final AppLovinSdkImpl f2551e;
    /* renamed from: f */
    private final Object f2552f = new Object();
    /* renamed from: g */
    private long f2553g;
    /* renamed from: h */
    private long f2554h;
    /* renamed from: i */
    private long f2555i;

    public C1280g(AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            AppLovinSdkImpl appLovinSdkImpl = (AppLovinSdkImpl) appLovinSdk;
            this.f2549c = appLovinSdkImpl.m2141b();
            this.f2550d = appLovinSdkImpl.m2139a();
            this.f2551e = appLovinSdkImpl;
            if (appLovinAd instanceof C1227q) {
                this.f2547a = (C1227q) appLovinAd;
                this.f2548b = this.f2547a.mo3994l();
                return;
            }
            this.f2547a = null;
            this.f2548b = 0;
        }
    }

    /* renamed from: a */
    static void m2905a(long j, C1227q c1227q, AppLovinSdkImpl appLovinSdkImpl) {
        if (c1227q != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.m2141b().m2420a(C1274b.f2101a, j, c1227q);
        }
    }

    /* renamed from: a */
    private void m2906a(C1274b c1274b) {
        synchronized (this.f2552f) {
            if (this.f2553g > 0) {
                this.f2549c.m2420a(c1274b, System.currentTimeMillis() - this.f2553g, this.f2547a);
            }
        }
    }

    /* renamed from: a */
    static void m2907a(C1227q c1227q, AppLovinSdkImpl appLovinSdkImpl) {
        if (c1227q != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.m2141b().m2420a(C1274b.f2102b, c1227q.m1782r(), c1227q);
            appLovinSdkImpl.m2141b().m2420a(C1274b.f2103c, c1227q.m1783s(), c1227q);
        }
    }

    /* renamed from: a */
    static void m2908a(C1298z c1298z, C1227q c1227q, AppLovinSdkImpl appLovinSdkImpl) {
        if (c1227q != null && appLovinSdkImpl != null && c1298z != null) {
            appLovinSdkImpl.m2141b().m2420a(C1274b.f2104d, c1298z.m3090a(), c1227q);
            appLovinSdkImpl.m2141b().m2420a(C1274b.f2105e, c1298z.m3092b(), c1227q);
        }
    }

    /* renamed from: a */
    public void m2909a() {
        long j = 0;
        this.f2549c.m2420a(C1274b.f2109i, this.f2550d.m2310a("ad_imp"), this.f2547a);
        this.f2549c.m2420a(C1274b.f2108h, this.f2550d.m2310a("ad_imp_session"), this.f2547a);
        synchronized (this.f2552f) {
            if (this.f2548b > 0) {
                this.f2553g = System.currentTimeMillis();
                this.f2549c.m2420a(C1274b.f2107g, this.f2553g - this.f2551e.getInitializedTimeMillis(), this.f2547a);
                this.f2549c.m2420a(C1274b.f2106f, this.f2553g - this.f2548b, this.f2547a);
                if (ag.m2242a(this.f2551e.getApplicationContext(), this.f2551e)) {
                    j = 1;
                }
                this.f2549c.m2420a(C1274b.f2115o, j, this.f2547a);
            }
        }
    }

    /* renamed from: a */
    public void m2910a(long j) {
        this.f2549c.m2420a(C1274b.f2116p, j, this.f2547a);
    }

    /* renamed from: b */
    public void m2911b() {
        synchronized (this.f2552f) {
            if (this.f2554h < 1) {
                this.f2554h = System.currentTimeMillis();
                if (this.f2553g > 0) {
                    this.f2549c.m2420a(C1274b.f2112l, this.f2554h - this.f2553g, this.f2547a);
                }
            }
        }
    }

    /* renamed from: b */
    public void m2912b(long j) {
        this.f2549c.m2420a(C1274b.f2117q, j, this.f2547a);
    }

    /* renamed from: c */
    public void m2913c() {
        m2906a(C1274b.f2110j);
    }

    /* renamed from: c */
    public void m2914c(long j) {
        synchronized (this.f2552f) {
            if (this.f2555i < 1) {
                this.f2555i = j;
                this.f2549c.m2420a(C1274b.f2118r, j, this.f2547a);
            }
        }
    }

    /* renamed from: d */
    public void m2915d() {
        m2906a(C1274b.f2113m);
    }

    /* renamed from: e */
    public void m2916e() {
        m2906a(C1274b.f2114n);
    }

    /* renamed from: f */
    public void m2917f() {
        m2906a(C1274b.f2111k);
    }
}
