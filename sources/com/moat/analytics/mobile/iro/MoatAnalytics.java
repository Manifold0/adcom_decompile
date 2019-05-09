package com.moat.analytics.mobile.iro;

import android.app.Application;
import android.support.annotation.UiThread;

public abstract class MoatAnalytics {
    /* renamed from: ˎ */
    private static MoatAnalytics f1116 = null;

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f1116 == null) {
                try {
                    f1116 = new C0774j();
                } catch (Exception e) {
                    C0785o.m1351(e);
                    f1116 = new com.moat.analytics.mobile.iro.NoOp.MoatAnalytics();
                }
            }
            moatAnalytics = f1116;
        }
        return moatAnalytics;
    }
}
