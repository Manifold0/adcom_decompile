package com.moat.analytics.mobile.vng;

import android.app.Application;
import com.moat.analytics.mobile.vng.C0870v.C0865a;

public abstract class MoatAnalytics {
    /* renamed from: a */
    private static MoatAnalytics f1334a = null;

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f1334a == null) {
                try {
                    f1334a = new C0847k();
                } catch (Exception e) {
                    C0849m.m1543a(e);
                    f1334a = new C0865a();
                }
            }
            moatAnalytics = f1334a;
        }
        return moatAnalytics;
    }

    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}
