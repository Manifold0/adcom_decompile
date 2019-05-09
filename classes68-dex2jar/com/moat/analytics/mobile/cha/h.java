// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import java.util.concurrent.TimeUnit;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

final class h
{
    private static final h \u02ca;
    private ScheduledFuture<?> \u02bd;
    private final Map<j, String> \u02cb;
    private ScheduledFuture<?> \u02ce;
    private final ScheduledExecutorService \u02cf;
    private final Map<d, String> \u0971;
    
    static {
        \u02ca = new h();
    }
    
    private h() {
        this.\u02cf = Executors.newScheduledThreadPool(1);
        this.\u02cb = new WeakHashMap();
        this.\u0971 = new WeakHashMap();
    }
    
    static h \u02ca() {
        return h.\u02ca;
    }
    
    final void \u02ca(final j j) {
        if (j != null) {
            a.\u02cf(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + j.hashCode());
            this.\u02cb.remove(j);
        }
    }
    
    final void \u02cb(final Context context, final j j) {
        if (j != null) {
            this.\u02cb.put(j, "");
            if (this.\u02bd == null || this.\u02bd.isDone()) {
                a.\u02cf(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.\u02bd = this.\u02cf.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (h.this.\u02cb.isEmpty()) {
                                h.this.\u02bd.cancel(true);
                            }
                        }
                        catch (Exception ex) {
                            o.\u02ce(ex);
                        }
                    }
                }, 0L, 50L, TimeUnit.MILLISECONDS);
            }
        }
    }
    
    final void \u0971(final Context context, final d d) {
        if (d != null) {
            a.\u02cf(3, "JSUpdateLooper", this, "addActiveTracker" + d.hashCode());
            if (!this.\u0971.containsKey(d)) {
                this.\u0971.put(d, "");
                if (this.\u02ce == null || this.\u02ce.isDone()) {
                    a.\u02cf(3, "JSUpdateLooper", this, "Starting view update loop");
                    this.\u02ce = this.\u02cf.scheduleWithFixedDelay(new Runnable() {
                        @Override
                        public final void run() {
                            try {
                                LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                                if (h.this.\u0971.isEmpty()) {
                                    a.\u02cf(3, "JSUpdateLooper", h.this, "No more active trackers");
                                    h.this.\u02ce.cancel(true);
                                }
                            }
                            catch (Exception ex) {
                                o.\u02ce(ex);
                            }
                        }
                    }, 0L, t.\u02cf().\u02ca, TimeUnit.MILLISECONDS);
                }
            }
        }
    }
    
    final void \u0971(final d d) {
        if (d != null) {
            a.\u02cf(3, "JSUpdateLooper", this, "removeActiveTracker" + d.hashCode());
            this.\u0971.remove(d);
        }
    }
}
