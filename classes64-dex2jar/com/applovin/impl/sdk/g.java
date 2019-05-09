// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;

public class g
{
    private final q a;
    private final long b;
    private final c c;
    private final aw d;
    private final AppLovinSdkImpl e;
    private final Object f;
    private long g;
    private long h;
    private long i;
    
    public g(final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        this.f = new Object();
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        final AppLovinSdkImpl e = (AppLovinSdkImpl)appLovinSdk;
        this.c = e.b();
        this.d = e.a();
        this.e = e;
        if (appLovinAd instanceof q) {
            this.a = (q)appLovinAd;
            this.b = this.a.l();
            return;
        }
        this.a = null;
        this.b = 0L;
    }
    
    static void a(final long n, final q q, final AppLovinSdkImpl appLovinSdkImpl) {
        if (q != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.b().a(b.a, n, q);
        }
    }
    
    private void a(final b b) {
        synchronized (this.f) {
            if (this.g > 0L) {
                this.c.a(b, System.currentTimeMillis() - this.g, this.a);
            }
        }
    }
    
    static void a(final q q, final AppLovinSdkImpl appLovinSdkImpl) {
        if (q != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.b().a(b.b, q.r(), q);
            appLovinSdkImpl.b().a(b.c, q.s(), q);
        }
    }
    
    static void a(final z z, final q q, final AppLovinSdkImpl appLovinSdkImpl) {
        if (q != null && appLovinSdkImpl != null && z != null) {
            appLovinSdkImpl.b().a(b.d, z.a(), q);
            appLovinSdkImpl.b().a(b.e, z.b(), q);
        }
    }
    
    public void a() {
        long n = 0L;
        this.c.a(com.applovin.impl.sdk.b.i, this.d.a("ad_imp"), this.a);
        this.c.a(com.applovin.impl.sdk.b.h, this.d.a("ad_imp_session"), this.a);
        synchronized (this.f) {
            if (this.b > 0L) {
                this.g = System.currentTimeMillis();
                this.c.a(com.applovin.impl.sdk.b.g, this.g - this.e.getInitializedTimeMillis(), this.a);
                this.c.a(com.applovin.impl.sdk.b.f, this.g - this.b, this.a);
                if (ag.a(this.e.getApplicationContext(), this.e)) {
                    n = 1L;
                }
                this.c.a(com.applovin.impl.sdk.b.o, n, this.a);
            }
        }
    }
    
    public void a(final long n) {
        this.c.a(com.applovin.impl.sdk.b.p, n, this.a);
    }
    
    public void b() {
        synchronized (this.f) {
            if (this.h < 1L) {
                this.h = System.currentTimeMillis();
                if (this.g > 0L) {
                    this.c.a(com.applovin.impl.sdk.b.l, this.h - this.g, this.a);
                }
            }
        }
    }
    
    public void b(final long n) {
        this.c.a(com.applovin.impl.sdk.b.q, n, this.a);
    }
    
    public void c() {
        this.a(com.applovin.impl.sdk.b.j);
    }
    
    public void c(final long i) {
        synchronized (this.f) {
            if (this.i < 1L) {
                this.i = i;
                this.c.a(com.applovin.impl.sdk.b.r, i, this.a);
            }
        }
    }
    
    public void d() {
        this.a(com.applovin.impl.sdk.b.m);
    }
    
    public void e() {
        this.a(com.applovin.impl.sdk.b.n);
    }
    
    public void f() {
        this.a(com.applovin.impl.sdk.b.k);
    }
}
