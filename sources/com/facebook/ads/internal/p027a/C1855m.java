package com.facebook.ads.internal.p027a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p051s.C2085c;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a.m */
public class C1855m {
    /* renamed from: a */
    private final C2085c f3867a;
    @Nullable
    /* renamed from: b */
    private Application f3868b;
    @Nullable
    /* renamed from: c */
    private C1854a f3869c;
    /* renamed from: d */
    private long f3870d = 0;
    @Nullable
    /* renamed from: e */
    private String f3871e = null;
    @Nullable
    /* renamed from: f */
    private C1841a f3872f = null;

    @TargetApi(14)
    /* renamed from: com.facebook.ads.internal.a.m$a */
    private static class C1854a implements ActivityLifecycleCallbacks {
        /* renamed from: a */
        private final WeakReference<Activity> f3865a;
        @Nullable
        /* renamed from: b */
        private C1855m f3866b;

        public C1854a(Activity activity, C1855m c1855m) {
            this.f3865a = new WeakReference(activity);
            this.f3866b = c1855m;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (this.f3866b != null) {
                Activity activity2 = (Activity) this.f3865a.get();
                if (activity2 == null || (activity2 != null && activity.equals(activity2))) {
                    this.f3866b.m4175a();
                    this.f3866b = null;
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    private C1855m(C2085c c2085c, Activity activity, int i) {
        this.f3867a = c2085c;
        this.f3868b = activity.getApplication();
        this.f3869c = new C1854a(activity, this);
    }

    /* renamed from: a */
    public static C1855m m4173a(C2085c c2085c, Activity activity) {
        int i = VERSION.SDK_INT;
        return (activity == null || i < 14) ? null : new C1855m(c2085c, activity, i);
    }

    /* renamed from: a */
    private void m4174a(String str, long j, long j2, @Nullable C1841a c1841a) {
        Map hashMap = new HashMap();
        hashMap.put("leave_time", Long.toString(j));
        hashMap.put("back_time", Long.toString(j2));
        if (c1841a != null) {
            hashMap.put("outcome", c1841a.name());
        }
        this.f3867a.mo5484m(str, hashMap);
    }

    @TargetApi(14)
    /* renamed from: a */
    public void m4175a() {
        m4174a(this.f3871e, this.f3870d, System.currentTimeMillis(), this.f3872f);
        if (this.f3868b != null && this.f3869c != null) {
            this.f3868b.unregisterActivityLifecycleCallbacks(this.f3869c);
            this.f3869c = null;
            this.f3868b = null;
        }
    }

    /* renamed from: a */
    public void m4176a(@Nullable C1841a c1841a) {
        this.f3872f = c1841a;
    }

    @TargetApi(14)
    /* renamed from: a */
    public void m4177a(String str) {
        this.f3871e = str;
        if (this.f3869c == null || this.f3868b == null) {
            m4174a(str, -1, -1, C1841a.CANNOT_TRACK);
            return;
        }
        this.f3870d = System.currentTimeMillis();
        this.f3868b.registerActivityLifecycleCallbacks(this.f3869c);
    }
}
