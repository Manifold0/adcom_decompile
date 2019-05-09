package com.facebook.ads.internal.p025w.p068a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.w.a.b */
public class C2564b implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    private static Context f6300a;
    /* renamed from: b */
    private static WeakReference<Activity> f6301b = new WeakReference(null);

    @Nullable
    /* renamed from: a */
    public static synchronized Activity m6613a() {
        Activity activity;
        synchronized (C2564b.class) {
            activity = (Activity) f6301b.get();
            Activity activity2 = null;
            Object obj = (activity == null || VERSION.SDK_INT < 28) ? 1 : null;
            if (obj != null) {
                activity2 = C2563a.m6612a();
            }
            if (!(f6300a == null || obj == null || activity == activity2)) {
                C2625a.m6741b(f6300a, "act_util", C2626b.f6535Z, new Exception("Activity discrepancies res: " + activity + ", ref: " + activity2));
            }
            if (activity == null) {
                activity = activity2;
            }
        }
        return activity;
    }

    /* renamed from: a */
    public static synchronized void m6614a(Context context) {
        synchronized (C2564b.class) {
            f6300a = context;
            if (f6300a instanceof Application) {
                ((Application) f6300a).registerActivityLifecycleCallbacks(new C2564b());
            } else {
                C2625a.m6741b(f6300a, "api", C2626b.f6550o, new Exception("AppContext is not Application."));
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        f6301b = new WeakReference(null);
    }

    public void onActivityResumed(Activity activity) {
        f6301b = new WeakReference(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
