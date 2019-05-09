package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.support.annotation.UiThread;

public abstract class MoatAnalytics {
    /* renamed from: ˏ */
    private static MoatAnalytics f3422 = null;

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f3422 == null) {
                try {
                    f3422 = new C1495f();
                } catch (Exception e) {
                    C1518o.m3840(e);
                    f3422 = new com.moat.analytics.mobile.cha.NoOp.MoatAnalytics();
                }
            }
            moatAnalytics = f3422;
        }
        return moatAnalytics;
    }
}
