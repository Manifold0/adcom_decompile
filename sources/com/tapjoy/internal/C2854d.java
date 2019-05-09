package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import java.util.Collections;
import java.util.Set;

/* renamed from: com.tapjoy.internal.d */
public final class C2854d {
    /* renamed from: a */
    private static Application f7303a;
    /* renamed from: b */
    private static int f7304b;
    /* renamed from: c */
    private static final cd f7305c = new cd();
    /* renamed from: d */
    private static final Set f7306d = Collections.synchronizedSet(new bd());
    /* renamed from: e */
    private static final cd f7307e = new cd();

    /* renamed from: b */
    public static boolean m7351b() {
        return f7304b > 0;
    }

    /* renamed from: c */
    public static Activity m7352c() {
        Activity activity = (Activity) f7305c.m7315a();
        if (activity != null) {
            return activity;
        }
        return (Activity) cw.m7343a(f7306d.iterator());
    }

    /* renamed from: a */
    public static void m7348a(Activity activity) {
        f7305c.m7316a(activity);
    }

    /* renamed from: a */
    public static synchronized void m7349a(Application application) {
        synchronized (C2854d.class) {
            if (f7303a != application) {
                f7303a = application;
            }
        }
    }

    /* renamed from: b */
    public static void m7350b(Activity activity) {
        f7304b++;
        f7305c.m7316a(activity);
        f7306d.add(activity);
    }

    /* renamed from: c */
    public static void m7353c(Activity activity) {
        f7304b--;
        f7305c.f7279a = null;
        f7306d.remove(activity);
        if (f7304b < 0) {
            f7304b = 0;
        }
    }

    /* renamed from: a */
    public static Activity m7347a() {
        Activity activity = (Activity) f7307e.m7315a();
        if (activity == null) {
            return C2854d.m7352c();
        }
        return activity;
    }
}
