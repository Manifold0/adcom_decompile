package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moat.analytics.mobile.cha.C1536t.C1532a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.cha.c */
final class C1492c {
    /* renamed from: ˊ */
    private static boolean f3460 = false;
    /* renamed from: ˋ */
    private static Application f3461;
    /* renamed from: ˎ */
    private static boolean f3462 = false;
    /* renamed from: ˏ */
    static WeakReference<Activity> f3463;
    /* renamed from: ॱ */
    private static int f3464 = 0;

    /* renamed from: com.moat.analytics.mobile.cha.c$a */
    static class C1491a implements ActivityLifecycleCallbacks {
        C1491a() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            C1492c.f3464 = 1;
        }

        public final void onActivityStarted(Activity activity) {
            try {
                C1492c.f3463 = new WeakReference(activity);
                C1492c.f3464 = 2;
                if (!C1492c.f3460) {
                    C1491a.m3744(true);
                }
                C1492c.f3460 = true;
                C1487a.m3715(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        public final void onActivityResumed(Activity activity) {
            try {
                C1492c.f3463 = new WeakReference(activity);
                C1492c.f3464 = 3;
                C1536t.m3887().m3893();
                C1487a.m3715(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C1495f) MoatAnalytics.getInstance()).f3473) {
                    C1493e.m3752(activity);
                }
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        public final void onActivityPaused(Activity activity) {
            try {
                C1492c.f3464 = 4;
                if (C1492c.m3746(activity)) {
                    C1492c.f3463 = new WeakReference(null);
                }
                C1487a.m3715(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        public final void onActivityStopped(Activity activity) {
            try {
                if (C1492c.f3464 != 3) {
                    C1492c.f3460 = false;
                    C1491a.m3744(false);
                }
                C1492c.f3464 = 5;
                if (C1492c.m3746(activity)) {
                    C1492c.f3463 = new WeakReference(null);
                }
                C1487a.m3715(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
            try {
                if (!(C1492c.f3464 == 3 || C1492c.f3464 == 5)) {
                    if (C1492c.f3460) {
                        C1491a.m3744(false);
                    }
                    C1492c.f3460 = false;
                }
                C1492c.f3464 = 6;
                C1487a.m3715(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C1492c.m3746(activity)) {
                    C1492c.f3463 = new WeakReference(null);
                }
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        /* renamed from: ॱ */
        private static void m3744(boolean z) {
            if (z) {
                C1487a.m3715(3, "ActivityState", null, "App became visible");
                if (C1536t.m3887().f3610 == C1532a.f3592 && !((C1495f) MoatAnalytics.getInstance()).f3471) {
                    C1517n.m3826().m3836();
                    return;
                }
                return;
            }
            C1487a.m3715(3, "ActivityState", null, "App became invisible");
            if (C1536t.m3887().f3610 == C1532a.f3592 && !((C1495f) MoatAnalytics.getInstance()).f3471) {
                C1517n.m3826().m3837();
            }
        }
    }

    C1492c() {
    }

    /* renamed from: ॱ */
    static void m3750(Application application) {
        f3461 = application;
        if (!f3462) {
            f3462 = true;
            f3461.registerActivityLifecycleCallbacks(new C1491a());
        }
    }

    /* renamed from: ˏ */
    static Application m3748() {
        return f3461;
    }

    /* renamed from: ˊ */
    static /* synthetic */ boolean m3746(Activity activity) {
        return f3463 != null && f3463.get() == activity;
    }
}
