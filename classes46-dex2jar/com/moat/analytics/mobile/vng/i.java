// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.concurrent.TimeUnit;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Map;

class i
{
    private static final i a;
    private final Map<j, String> b;
    private final Map<b, String> c;
    private final ScheduledExecutorService d;
    private ScheduledFuture<?> e;
    private ScheduledFuture<?> f;
    
    static {
        a = new i();
    }
    
    private i() {
        this.d = Executors.newScheduledThreadPool(1);
        this.b = new WeakHashMap<j, String>();
        this.c = new WeakHashMap<b, String>();
    }
    
    static i a() {
        return i.a;
    }
    
    private void a(final Context context) {
        if (this.f == null || this.f.isDone()) {
            p.a(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
            this.f = this.d.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (i.this.b.isEmpty()) {
                            i.this.f.cancel(true);
                        }
                    }
                    catch (Exception ex) {
                        m.a(ex);
                    }
                }
            }, 0L, 50L, TimeUnit.MILLISECONDS);
        }
    }
    
    private void b(final Context context) {
        if (this.e == null || this.e.isDone()) {
            p.a(3, "JSUpdateLooper", this, "Starting view update loop");
            this.e = this.d.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.c.isEmpty()) {
                            p.a(3, "JSUpdateLooper", i.this, "No more active trackers");
                            i.this.e.cancel(true);
                        }
                    }
                    catch (Exception ex) {
                        m.a(ex);
                    }
                }
            }, 0L, w.a().d, TimeUnit.MILLISECONDS);
        }
    }
    
    void a(final Context context, final b b) {
        if (b != null) {
            p.a(3, "JSUpdateLooper", this, "addActiveTracker" + b.hashCode());
            if (this.c != null && !this.c.containsKey(b)) {
                this.c.put(b, "");
                this.b(context);
            }
        }
    }
    
    void a(final Context context, final j j) {
        if (this.b != null && j != null) {
            this.b.put(j, "");
            this.a(context);
        }
    }
    
    void a(final b b) {
        if (b != null) {
            p.a(3, "JSUpdateLooper", this, "removeActiveTracker" + b.hashCode());
            if (this.c != null) {
                this.c.remove(b);
            }
        }
    }
    
    void a(final j j) {
        if (j != null) {
            p.a(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + j.hashCode());
            if (this.b != null) {
                this.b.remove(j);
            }
        }
    }
}
