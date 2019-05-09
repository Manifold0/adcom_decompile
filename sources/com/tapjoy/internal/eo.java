package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyUtil;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public final class eo {
    /* renamed from: a */
    private static final eo f7670a = new eo();
    /* renamed from: b */
    private Application f7671b;
    /* renamed from: c */
    private ActivityLifecycleCallbacks f7672c;
    /* renamed from: d */
    private final HashSet f7673d = new HashSet();

    /* renamed from: a */
    public static void m7658a(Context context) {
        if (VERSION.SDK_INT >= 14 && context != null) {
            eo eoVar = f7670a;
            Context applicationContext = context.getApplicationContext();
            if (eoVar.f7671b == null) {
                try {
                    if (applicationContext instanceof Application) {
                        eoVar.f7671b = (Application) applicationContext;
                    } else {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        TapjoyUtil.runOnMainThread(new Runnable(eoVar) {
                            /* renamed from: b */
                            final /* synthetic */ eo f7667b;

                            public final void run() {
                                try {
                                    this.f7667b.f7671b = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null));
                                } catch (Throwable e) {
                                    TapjoyLog.m7131w("Tapjoy.ActivityTracker", Log.getStackTraceString(e));
                                } finally {
                                    countDownLatch.countDown();
                                }
                            }
                        });
                        countDownLatch.await();
                    }
                } catch (Throwable e) {
                    TapjoyLog.m7131w("Tapjoy.ActivityTracker", Log.getStackTraceString(e));
                }
                if (eoVar.f7671b == null) {
                    return;
                }
            }
            synchronized (eoVar) {
                if (eoVar.f7672c == null) {
                    Activity c = C2854d.m7352c();
                    if (c != null) {
                        eoVar.f7673d.add(m7660b(c));
                    }
                    final HashSet hashSet = eoVar.f7673d;
                    eoVar.f7672c = new ActivityLifecycleCallbacks(eoVar) {
                        /* renamed from: b */
                        final /* synthetic */ eo f7669b;

                        public final void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        }

                        public final void onActivityStarted(Activity activity) {
                            hashSet.add(eo.m7660b(activity));
                            if (hashSet.size() == 1) {
                                fq.m7774a();
                            }
                            C2854d.m7348a(activity);
                        }

                        public final void onActivityResumed(Activity activity) {
                        }

                        public final void onActivityPaused(Activity activity) {
                        }

                        public final void onActivityStopped(Activity activity) {
                            hashSet.remove(eo.m7660b(activity));
                            if (hashSet.size() <= 0) {
                                fq.m7779b();
                            }
                        }

                        public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                        }

                        public final void onActivityDestroyed(Activity activity) {
                        }
                    };
                    eoVar.f7671b.registerActivityLifecycleCallbacks(eoVar.f7672c);
                    fq.m7774a();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m7657a() {
        if (VERSION.SDK_INT >= 14) {
            eo eoVar = f7670a;
            if (eoVar.f7671b != null) {
                synchronized (eoVar) {
                    if (eoVar.f7672c != null) {
                        eoVar.f7671b.unregisterActivityLifecycleCallbacks(eoVar.f7672c);
                        eoVar.f7672c = null;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private static String m7660b(Activity activity) {
        return activity.getClass().getName() + "@" + System.identityHashCode(activity);
    }
}
