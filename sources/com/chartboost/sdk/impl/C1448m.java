package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1387b;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.aj.C1406a;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.impl.m */
public class C1448m implements C1406a {
    /* renamed from: a */
    private final C1447l f3306a;
    /* renamed from: b */
    private final C1378f f3307b;
    /* renamed from: c */
    private final ah f3308c;
    /* renamed from: d */
    private final ap f3309d;
    /* renamed from: e */
    private final C1391a f3310e;
    /* renamed from: f */
    private final AtomicReference<C1390e> f3311f;
    /* renamed from: g */
    private int f3312g = 1;
    /* renamed from: h */
    private int f3313h = 0;
    /* renamed from: i */
    private long f3314i = 0;
    /* renamed from: j */
    private aj f3315j = null;
    /* renamed from: k */
    private AtomicInteger f3316k = null;

    public C1448m(C1447l c1447l, C1378f c1378f, ah ahVar, ap apVar, C1391a c1391a, AtomicReference<C1390e> atomicReference) {
        this.f3306a = c1447l;
        this.f3307b = c1378f;
        this.f3308c = ahVar;
        this.f3309d = apVar;
        this.f3310e = c1391a;
        this.f3311f = atomicReference;
    }

    /* renamed from: a */
    public synchronized void m3604a() {
        try {
            CBLogging.m3099b("Chartboost SDK", "Sdk Version = 7.3.0, Commit: d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
            C1390e c1390e = (C1390e) this.f3311f.get();
            m3603a(c1390e);
            if (c1390e.f2795c || c1390e.f2794b || !C1410i.f2945v) {
                m3607b();
            } else {
                if (this.f3312g == 3) {
                    if (this.f3316k.get() <= 0) {
                        CBLogging.m3097a("Prefetcher", "Change state to COOLDOWN");
                        this.f3312g = 4;
                        this.f3316k = null;
                    }
                }
                if (this.f3312g == 4) {
                    if (this.f3314i - System.nanoTime() > 0) {
                        CBLogging.m3097a("Prefetcher", "Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
                    } else {
                        CBLogging.m3097a("Prefetcher", "Change state to IDLE");
                        this.f3312g = 1;
                        this.f3313h = 0;
                        this.f3314i = 0;
                    }
                }
                if (this.f3312g == 1) {
                    aj amVar;
                    if (c1390e.f2817y) {
                        amVar = new am(c1390e.f2788H, this.f3309d, this.f3310e, 2, this);
                        amVar.m3400a("cache_assets", this.f3307b.m3138c(), 0);
                        amVar.l = true;
                        CBLogging.m3097a("Prefetcher", "Change state to AWAIT_PREFETCH_RESPONSE");
                        this.f3312g = 2;
                        this.f3313h = 2;
                        this.f3314i = System.nanoTime() + TimeUnit.MINUTES.toNanos((long) c1390e.f2784D);
                        this.f3315j = amVar;
                    } else if (c1390e.f2797e) {
                        amVar = new aj("/api/video-prefetch", this.f3309d, this.f3310e, 2, this);
                        amVar.m3386a("local-videos", this.f3307b.m3136b());
                        amVar.f3010l = true;
                        CBLogging.m3097a("Prefetcher", "Change state to AWAIT_PREFETCH_RESPONSE");
                        this.f3312g = 2;
                        this.f3313h = 1;
                        this.f3314i = System.nanoTime() + TimeUnit.MINUTES.toNanos((long) c1390e.f2801i);
                        this.f3315j = amVar;
                    } else {
                        CBLogging.m3099b("Prefetcher", "Did not prefetch because neither native nor webview are enabled.");
                    }
                    this.f3308c.m3371a(this.f3315j);
                }
            }
        } catch (Exception e) {
            if (this.f3312g == 2) {
                CBLogging.m3097a("Prefetcher", "Change state to COOLDOWN");
                this.f3312g = 4;
                this.f3315j = null;
            }
            C1391a.m3206a(getClass(), "prefetch", e);
        }
    }

    /* renamed from: a */
    private void m3603a(C1390e c1390e) {
        boolean z = c1390e.f2817y;
        int i = (z || !c1390e.f2797e) ? 0 : 1;
        if (!(this.f3313h == 1 && i == 0) && (this.f3313h != 2 || z)) {
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            CBLogging.m3097a("Prefetcher", "Change state to IDLE");
            this.f3312g = 1;
            this.f3313h = 0;
            this.f3314i = 0;
            this.f3315j = null;
            AtomicInteger atomicInteger = this.f3316k;
            this.f3316k = null;
            if (atomicInteger != null) {
                this.f3306a.m3600a(atomicInteger);
            }
        }
    }

    /* renamed from: b */
    public synchronized void m3607b() {
        if (this.f3312g == 2) {
            CBLogging.m3097a("Prefetcher", "Change state to COOLDOWN");
            this.f3312g = 4;
            this.f3315j = null;
        } else if (this.f3312g == 3) {
            CBLogging.m3097a("Prefetcher", "Change state to COOLDOWN");
            this.f3312g = 4;
            AtomicInteger atomicInteger = this.f3316k;
            this.f3316k = null;
            if (atomicInteger != null) {
                this.f3306a.m3600a(atomicInteger);
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo4278a(aj ajVar, JSONObject jSONObject) {
        try {
            if (this.f3312g == 2) {
                if (ajVar == this.f3315j) {
                    CBLogging.m3097a("Prefetcher", "Change state to DOWNLOAD_ASSETS");
                    this.f3312g = 3;
                    this.f3315j = null;
                    this.f3316k = new AtomicInteger();
                    if (jSONObject != null) {
                        CBLogging.m3097a("Prefetcher", "Got Asset list for Prefetch from server :)" + jSONObject);
                        if (this.f3313h == 1) {
                            this.f3306a.m3598a(3, C1387b.m3164a(jSONObject), this.f3316k, null);
                        } else if (this.f3313h == 2) {
                            this.f3306a.m3598a(3, C1387b.m3165a(jSONObject, ((C1390e) this.f3311f.get()).f2814v), this.f3316k, null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onSuccess", e);
        }
    }

    /* renamed from: a */
    public synchronized void mo4277a(aj ajVar, CBError cBError) {
        if (this.f3312g == 2) {
            if (ajVar == this.f3315j) {
                this.f3315j = null;
                CBLogging.m3097a("Prefetcher", "Change state to COOLDOWN");
                this.f3312g = 4;
            }
        }
    }
}
