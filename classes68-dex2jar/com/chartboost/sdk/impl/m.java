// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.b;
import org.json.JSONObject;
import com.chartboost.sdk.Model.CBError;
import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.i;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.concurrent.atomic.AtomicInteger;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.Libraries.f;

public class m implements a
{
    private final l a;
    private final f b;
    private final ah c;
    private final ap d;
    private final com.chartboost.sdk.Tracking.a e;
    private final AtomicReference<e> f;
    private int g;
    private int h;
    private long i;
    private aj j;
    private AtomicInteger k;
    
    public m(final l a, final f b, final ah c, final ap d, final com.chartboost.sdk.Tracking.a e, final AtomicReference<e> f) {
        this.g = 1;
        this.h = 0;
        this.i = 0L;
        this.j = null;
        this.k = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    private void a(final e e) {
        final boolean y = e.y;
        int n;
        if (!y && e.e) {
            n = 1;
        }
        else {
            n = 0;
        }
        int n2;
        if ((this.h == 1 && n == 0) || (this.h == 2 && !y)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            CBLogging.a("Prefetcher", "Change state to IDLE");
            this.g = 1;
            this.h = 0;
            this.i = 0L;
            this.j = null;
            final AtomicInteger k = this.k;
            this.k = null;
            if (k != null) {
                this.a.a(k);
            }
        }
    }
    
    public void a() {
        while (true) {
            Label_0184: {
                Label_0162: {
                    synchronized (this) {
                        try {
                            CBLogging.b("Chartboost SDK", "Sdk Version = 7.3.0, Commit: d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
                            final e e = this.f.get();
                            this.a(e);
                            if (e.c || e.b || !com.chartboost.sdk.i.v) {
                                this.b();
                            }
                            else {
                                if (this.g == 3) {
                                    if (this.k.get() > 0) {
                                        return;
                                    }
                                    CBLogging.a("Prefetcher", "Change state to COOLDOWN");
                                    this.g = 4;
                                    this.k = null;
                                }
                                if (this.g != 4) {
                                    break Label_0184;
                                }
                                if (this.i - System.nanoTime() <= 0L) {
                                    break Label_0162;
                                }
                                CBLogging.a("Prefetcher", "Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
                            }
                            return;
                        }
                        catch (Exception ex) {
                            if (this.g == 2) {
                                CBLogging.a("Prefetcher", "Change state to COOLDOWN");
                                this.g = 4;
                                this.j = null;
                            }
                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "prefetch", ex);
                            return;
                        }
                    }
                }
                CBLogging.a("Prefetcher", "Change state to IDLE");
                this.g = 1;
                this.h = 0;
                this.i = 0L;
            }
            if (this.g == 1) {
                final e e2;
                if (e2.y) {
                    final am j = new am(e2.H, this.d, this.e, 2, this);
                    j.a("cache_assets", this.b.c(), 0);
                    j.l = true;
                    CBLogging.a("Prefetcher", "Change state to AWAIT_PREFETCH_RESPONSE");
                    this.g = 2;
                    this.h = 2;
                    this.i = System.nanoTime() + TimeUnit.MINUTES.toNanos(e2.D);
                    this.j = j;
                }
                else {
                    if (!e2.e) {
                        CBLogging.b("Prefetcher", "Did not prefetch because neither native nor webview are enabled.");
                        return;
                    }
                    final aj i = new aj("/api/video-prefetch", this.d, this.e, 2, (aj.a)this);
                    i.a("local-videos", this.b.b());
                    i.l = true;
                    CBLogging.a("Prefetcher", "Change state to AWAIT_PREFETCH_RESPONSE");
                    this.g = 2;
                    this.h = 1;
                    this.i = System.nanoTime() + TimeUnit.MINUTES.toNanos(e2.i);
                    this.j = i;
                }
                this.c.a((ad<Object>)this.j);
            }
        }
    }
    
    @Override
    public void a(final aj aj, final CBError cbError) {
        synchronized (this) {
            if (this.g == 2 && aj == this.j) {
                this.j = null;
                CBLogging.a("Prefetcher", "Change state to COOLDOWN");
                this.g = 4;
            }
        }
    }
    
    @Override
    public void a(final aj aj, final JSONObject jsonObject) {
        while (true) {
            Label_0128: {
                synchronized (this) {
                    try {
                        if (this.g == 2 && aj == this.j) {
                            CBLogging.a("Prefetcher", "Change state to DOWNLOAD_ASSETS");
                            this.g = 3;
                            this.j = null;
                            this.k = new AtomicInteger();
                            if (jsonObject != null) {
                                CBLogging.a("Prefetcher", "Got Asset list for Prefetch from server :)" + jsonObject);
                                if (this.h != 1) {
                                    break Label_0128;
                                }
                                this.a.a(3, com.chartboost.sdk.Model.b.a(jsonObject), this.k, null);
                            }
                        }
                        return;
                    }
                    catch (Exception ex) {
                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "onSuccess", ex);
                        return;
                    }
                }
            }
            if (this.h == 2) {
                this.a.a(3, com.chartboost.sdk.Model.b.a(jsonObject, this.f.get().v), this.k, null);
            }
        }
    }
    
    public void b() {
        synchronized (this) {
            if (this.g == 2) {
                CBLogging.a("Prefetcher", "Change state to COOLDOWN");
                this.g = 4;
                this.j = null;
            }
            else if (this.g == 3) {
                CBLogging.a("Prefetcher", "Change state to COOLDOWN");
                this.g = 4;
                final AtomicInteger k = this.k;
                this.k = null;
                if (k != null) {
                    this.a.a(k);
                }
            }
        }
    }
}
