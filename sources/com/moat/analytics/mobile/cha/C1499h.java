package com.moat.analytics.mobile.cha;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.cha.h */
final class C1499h {
    /* renamed from: ˊ */
    private static final C1499h f3490 = new C1499h();
    /* renamed from: ʽ */
    private ScheduledFuture<?> f3491;
    /* renamed from: ˋ */
    private final Map<C1507j, String> f3492 = new WeakHashMap();
    /* renamed from: ˎ */
    private ScheduledFuture<?> f3493;
    /* renamed from: ˏ */
    private final ScheduledExecutorService f3494 = Executors.newScheduledThreadPool(1);
    /* renamed from: ॱ */
    private final Map<C1489d, String> f3495 = new WeakHashMap();

    /* renamed from: ˊ */
    static C1499h m3768() {
        return f3490;
    }

    private C1499h() {
    }

    /* renamed from: ˋ */
    final void m3774(final Context context, C1507j c1507j) {
        if (c1507j != null) {
            this.f3492.put(c1507j, "");
            if (this.f3491 == null || this.f3491.isDone()) {
                C1487a.m3715(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.f3491 = this.f3494.scheduleWithFixedDelay(new Runnable(this) {
                    /* renamed from: ॱ */
                    private /* synthetic */ C1499h f3487;

                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (this.f3487.f3492.isEmpty()) {
                                this.f3487.f3491.cancel(true);
                            }
                        } catch (Exception e) {
                            C1518o.m3840(e);
                        }
                    }
                }, 0, 50, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* renamed from: ˊ */
    final void m3773(C1507j c1507j) {
        if (c1507j != null) {
            C1487a.m3715(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + c1507j.hashCode());
            this.f3492.remove(c1507j);
        }
    }

    /* renamed from: ॱ */
    final void m3775(final Context context, C1489d c1489d) {
        if (c1489d != null) {
            C1487a.m3715(3, "JSUpdateLooper", this, "addActiveTracker" + c1489d.hashCode());
            if (!this.f3495.containsKey(c1489d)) {
                this.f3495.put(c1489d, "");
                if (this.f3493 == null || this.f3493.isDone()) {
                    C1487a.m3715(3, "JSUpdateLooper", this, "Starting view update loop");
                    this.f3493 = this.f3494.scheduleWithFixedDelay(new Runnable(this) {
                        /* renamed from: ˎ */
                        private /* synthetic */ C1499h f3489;

                        public final void run() {
                            try {
                                LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                                if (this.f3489.f3495.isEmpty()) {
                                    C1487a.m3715(3, "JSUpdateLooper", this.f3489, "No more active trackers");
                                    this.f3489.f3493.cancel(true);
                                }
                            } catch (Exception e) {
                                C1518o.m3840(e);
                            }
                        }
                    }, 0, (long) C1536t.m3887().f3606, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /* renamed from: ॱ */
    final void m3776(C1489d c1489d) {
        if (c1489d != null) {
            C1487a.m3715(3, "JSUpdateLooper", this, "removeActiveTracker" + c1489d.hashCode());
            this.f3495.remove(c1489d);
        }
    }
}
