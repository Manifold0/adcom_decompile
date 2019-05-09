package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.vng.i */
class C0840i {
    /* renamed from: a */
    private static final C0840i f1396a = new C0840i();
    /* renamed from: b */
    private final Map<C0845j, String> f1397b = new WeakHashMap();
    /* renamed from: c */
    private final Map<C0822b, String> f1398c = new WeakHashMap();
    /* renamed from: d */
    private final ScheduledExecutorService f1399d = Executors.newScheduledThreadPool(1);
    /* renamed from: e */
    private ScheduledFuture<?> f1400e;
    /* renamed from: f */
    private ScheduledFuture<?> f1401f;

    private C0840i() {
    }

    /* renamed from: a */
    static C0840i m1486a() {
        return f1396a;
    }

    /* renamed from: a */
    private void m1488a(final Context context) {
        if (this.f1401f == null || this.f1401f.isDone()) {
            C0858p.m1577a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f1401f = this.f1399d.scheduleWithFixedDelay(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0840i f1393b;

                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (this.f1393b.f1397b.isEmpty()) {
                            this.f1393b.f1401f.cancel(true);
                        }
                    } catch (Exception e) {
                        C0849m.m1543a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: b */
    private void m1490b(final Context context) {
        if (this.f1400e == null || this.f1400e.isDone()) {
            C0858p.m1577a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.f1400e = this.f1399d.scheduleWithFixedDelay(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0840i f1395b;

                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (this.f1395b.f1398c.isEmpty()) {
                            C0858p.m1577a(3, "JSUpdateLooper", this.f1395b, "No more active trackers");
                            this.f1395b.f1400e.cancel(true);
                        }
                    } catch (Exception e) {
                        C0849m.m1543a(e);
                    }
                }
            }, 0, (long) C0879w.m1610a().f1481d, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: a */
    void m1493a(Context context, C0822b c0822b) {
        if (c0822b != null) {
            C0858p.m1577a(3, "JSUpdateLooper", (Object) this, "addActiveTracker" + c0822b.hashCode());
            if (this.f1398c != null && !this.f1398c.containsKey(c0822b)) {
                this.f1398c.put(c0822b, "");
                m1490b(context);
            }
        }
    }

    /* renamed from: a */
    void m1494a(Context context, C0845j c0845j) {
        if (this.f1397b != null && c0845j != null) {
            this.f1397b.put(c0845j, "");
            m1488a(context);
        }
    }

    /* renamed from: a */
    void m1495a(C0822b c0822b) {
        if (c0822b != null) {
            C0858p.m1577a(3, "JSUpdateLooper", (Object) this, "removeActiveTracker" + c0822b.hashCode());
            if (this.f1398c != null) {
                this.f1398c.remove(c0822b);
            }
        }
    }

    /* renamed from: a */
    void m1496a(C0845j c0845j) {
        if (c0845j != null) {
            C0858p.m1577a(3, "JSUpdateLooper", (Object) this, "removeSetupNeededBridge" + c0845j.hashCode());
            if (this.f1397b != null) {
                this.f1397b.remove(c0845j);
            }
        }
    }
}
