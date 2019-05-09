package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.vng.a */
class C0821a {
    /* renamed from: a */
    static WeakReference<Activity> f1339a;
    /* renamed from: b */
    private static boolean f1340b = false;
    /* renamed from: c */
    private static Application f1341c;
    /* renamed from: d */
    private static int f1342d = 0;
    /* renamed from: e */
    private static boolean f1343e = false;

    /* renamed from: com.moat.analytics.mobile.vng.a$a */
    private static class C0818a implements ActivityLifecycleCallbacks {
        C0818a() {
        }

        /* renamed from: a */
        private static void m1430a(boolean z) {
            if (z) {
                C0858p.m1577a(3, "ActivityState", null, "App became visible");
                if (C0879w.m1610a().f1478a == C0878d.ON && !((C0847k) MoatAnalytics.getInstance()).f1425c) {
                    C0857o.m1554a().m1574c();
                    return;
                }
                return;
            }
            C0858p.m1577a(3, "ActivityState", null, "App became invisible");
            if (C0879w.m1610a().f1478a == C0878d.ON && !((C0847k) MoatAnalytics.getInstance()).f1425c) {
                C0857o.m1554a().m1575d();
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            C0821a.f1342d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(C0821a.f1342d == 3 || C0821a.f1342d == 5)) {
                    if (C0821a.f1343e) {
                        C0818a.m1430a(false);
                    }
                    C0821a.f1343e = false;
                }
                C0821a.f1342d = 6;
                C0858p.m1577a(3, "ActivityState", (Object) this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C0821a.m1444b(activity)) {
                    C0821a.f1339a = new WeakReference(null);
                }
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                C0821a.f1342d = 4;
                if (C0821a.m1444b(activity)) {
                    C0821a.f1339a = new WeakReference(null);
                }
                C0858p.m1577a(3, "ActivityState", (Object) this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                C0821a.f1339a = new WeakReference(activity);
                C0821a.f1342d = 3;
                C0879w.m1610a().m1623b();
                C0858p.m1577a(3, "ActivityState", (Object) this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C0847k) MoatAnalytics.getInstance()).f1424b) {
                    C0830f.m1469a(activity);
                }
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                C0821a.f1339a = new WeakReference(activity);
                C0821a.f1342d = 2;
                if (!C0821a.f1343e) {
                    C0818a.m1430a(true);
                }
                C0821a.f1343e = true;
                C0858p.m1577a(3, "ActivityState", (Object) this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (C0821a.f1342d != 3) {
                    C0821a.f1343e = false;
                    C0818a.m1430a(false);
                }
                C0821a.f1342d = 5;
                if (C0821a.m1444b(activity)) {
                    C0821a.f1339a = new WeakReference(null);
                }
                C0858p.m1577a(3, "ActivityState", (Object) this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    C0821a() {
    }

    /* renamed from: a */
    static Application m1439a() {
        return f1341c;
    }

    /* renamed from: a */
    static void m1440a(Application application) {
        f1341c = application;
        if (!f1340b) {
            f1340b = true;
            f1341c.registerActivityLifecycleCallbacks(new C0818a());
        }
    }

    /* renamed from: b */
    private static boolean m1444b(Activity activity) {
        return f1339a != null && f1339a.get() == activity;
    }
}
