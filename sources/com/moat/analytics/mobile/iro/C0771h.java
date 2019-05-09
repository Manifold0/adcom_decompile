package com.moat.analytics.mobile.iro;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.iro.h */
final class C0771h {
    /* renamed from: ˎ */
    private static final C0771h f1203 = new C0771h();
    /* renamed from: ʽ */
    private ScheduledFuture<?> f1204;
    /* renamed from: ˊ */
    private final ScheduledExecutorService f1205 = Executors.newScheduledThreadPool(1);
    /* renamed from: ˋ */
    private final Map<C0765f, String> f1206 = new WeakHashMap();
    /* renamed from: ˏ */
    private ScheduledFuture<?> f1207;
    /* renamed from: ॱ */
    private final Map<C0757c, String> f1208 = new WeakHashMap();

    /* renamed from: ˋ */
    static C0771h m1300() {
        return f1203;
    }

    private C0771h() {
    }

    /* renamed from: ˎ */
    final void m1305(final Context context, C0765f c0765f) {
        if (c0765f != null) {
            this.f1206.put(c0765f, "");
            if (this.f1204 == null || this.f1204.isDone()) {
                C0756b.m1234(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.f1204 = this.f1205.scheduleWithFixedDelay(new Runnable(this) {
                    /* renamed from: ॱ */
                    private /* synthetic */ C0771h f1200;

                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (this.f1200.f1206.isEmpty()) {
                                this.f1200.f1204.cancel(true);
                            }
                        } catch (Exception e) {
                            C0785o.m1351(e);
                        }
                    }
                }, 0, 50, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* renamed from: ˎ */
    final void m1306(C0765f c0765f) {
        if (c0765f != null) {
            C0756b.m1234(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + c0765f.hashCode());
            this.f1206.remove(c0765f);
        }
    }

    /* renamed from: ˋ */
    final void m1304(final Context context, C0757c c0757c) {
        if (c0757c != null) {
            C0756b.m1234(3, "JSUpdateLooper", this, "addActiveTracker" + c0757c.hashCode());
            if (!this.f1208.containsKey(c0757c)) {
                this.f1208.put(c0757c, "");
                if (this.f1207 == null || this.f1207.isDone()) {
                    C0756b.m1234(3, "JSUpdateLooper", this, "Starting view update loop");
                    this.f1207 = this.f1205.scheduleWithFixedDelay(new Runnable(this) {
                        /* renamed from: ˎ */
                        private /* synthetic */ C0771h f1202;

                        public final void run() {
                            try {
                                LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                                if (this.f1202.f1208.isEmpty()) {
                                    C0756b.m1234(3, "JSUpdateLooper", this.f1202, "No more active trackers");
                                    this.f1202.f1207.cancel(true);
                                }
                            } catch (Exception e) {
                                C0785o.m1351(e);
                            }
                        }
                    }, 0, (long) C0803t.m1393().f1303, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /* renamed from: ॱ */
    final void m1307(C0757c c0757c) {
        if (c0757c != null) {
            C0756b.m1234(3, "JSUpdateLooper", this, "removeActiveTracker" + c0757c.hashCode());
            this.f1208.remove(c0757c);
        }
    }
}
