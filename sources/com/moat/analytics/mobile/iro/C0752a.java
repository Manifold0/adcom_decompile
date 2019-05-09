package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.iro.a */
final class C0752a {
    /* renamed from: ˊ */
    static WeakReference<Activity> f1120;
    /* renamed from: ˋ */
    private static boolean f1121 = false;
    /* renamed from: ˎ */
    private static boolean f1122 = false;
    /* renamed from: ˏ */
    private static Application f1123;
    /* renamed from: ॱ */
    private static int f1124 = 0;

    /* renamed from: com.moat.analytics.mobile.iro.a$c */
    static class C0751c implements ActivityLifecycleCallbacks {
        C0751c() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            C0752a.f1124 = 1;
        }

        public final void onActivityStarted(Activity activity) {
            try {
                C0752a.f1120 = new WeakReference(activity);
                C0752a.f1124 = 2;
                if (!C0752a.f1122) {
                    C0751c.m1222(true);
                }
                C0752a.f1122 = true;
                C0756b.m1234(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        public final void onActivityResumed(Activity activity) {
            try {
                C0752a.f1120 = new WeakReference(activity);
                C0752a.f1124 = 3;
                C0803t.m1393().m1402();
                C0756b.m1234(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C0774j) MoatAnalytics.getInstance()).f1222) {
                    C0760e.m1262(activity);
                }
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        public final void onActivityPaused(Activity activity) {
            try {
                C0752a.f1124 = 4;
                if (C0752a.m1227(activity)) {
                    C0752a.f1120 = new WeakReference(null);
                }
                C0756b.m1234(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        public final void onActivityStopped(Activity activity) {
            try {
                if (C0752a.f1124 != 3) {
                    C0752a.f1122 = false;
                    C0751c.m1222(false);
                }
                C0752a.f1124 = 5;
                if (C0752a.m1227(activity)) {
                    C0752a.f1120 = new WeakReference(null);
                }
                C0756b.m1234(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
            try {
                if (!(C0752a.f1124 == 3 || C0752a.f1124 == 5)) {
                    if (C0752a.f1122) {
                        C0751c.m1222(false);
                    }
                    C0752a.f1122 = false;
                }
                C0752a.f1124 = 6;
                C0756b.m1234(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C0752a.m1227(activity)) {
                    C0752a.f1120 = new WeakReference(null);
                }
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        /* renamed from: ˋ */
        private static void m1222(boolean z) {
            if (z) {
                C0756b.m1234(3, "ActivityState", null, "App became visible");
                if (C0803t.m1393().f1298 == C0800c.f1286 && !((C0774j) MoatAnalytics.getInstance()).f1223) {
                    C0777k.m1332().m1340();
                    return;
                }
                return;
            }
            C0756b.m1234(3, "ActivityState", null, "App became invisible");
            if (C0803t.m1393().f1298 == C0800c.f1286 && !((C0774j) MoatAnalytics.getInstance()).f1223) {
                C0777k.m1332().m1339();
            }
        }
    }

    C0752a() {
    }

    /* renamed from: ˊ */
    static void m1224(Application application) {
        f1123 = application;
        if (!f1121) {
            f1121 = true;
            f1123.registerActivityLifecycleCallbacks(new C0751c());
        }
    }

    /* renamed from: ˎ */
    static Application m1226() {
        return f1123;
    }

    /* renamed from: ˎ */
    static /* synthetic */ boolean m1227(Activity activity) {
        return f1120 != null && f1120.get() == activity;
    }
}
