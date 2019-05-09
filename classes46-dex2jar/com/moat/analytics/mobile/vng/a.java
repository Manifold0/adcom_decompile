// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.os.Bundle;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.Activity;
import java.lang.ref.WeakReference;

class a
{
    static WeakReference<Activity> a;
    private static boolean b;
    private static Application c;
    private static int d;
    private static boolean e;
    
    static {
        com.moat.analytics.mobile.vng.a.b = false;
        com.moat.analytics.mobile.vng.a.d = 0;
        com.moat.analytics.mobile.vng.a.e = false;
    }
    
    static Application a() {
        return com.moat.analytics.mobile.vng.a.c;
    }
    
    static void a(final Application c) {
        com.moat.analytics.mobile.vng.a.c = c;
        if (!com.moat.analytics.mobile.vng.a.b) {
            com.moat.analytics.mobile.vng.a.b = true;
            com.moat.analytics.mobile.vng.a.c.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new a());
        }
    }
    
    private static boolean b(final Activity activity) {
        return com.moat.analytics.mobile.vng.a.a != null && com.moat.analytics.mobile.vng.a.a.get() == activity;
    }
    
    private static class a implements Application$ActivityLifecycleCallbacks
    {
        a() {
        }
        
        private static void a(final boolean b) {
            if (b) {
                p.a(3, "ActivityState", null, "App became visible");
                if (w.a().a == w.d.b && !((k)MoatAnalytics.getInstance()).c) {
                    o.a().c();
                }
            }
            else {
                p.a(3, "ActivityState", null, "App became invisible");
                if (w.a().a == w.d.b && !((k)MoatAnalytics.getInstance()).c) {
                    o.a().d();
                }
            }
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            com.moat.analytics.mobile.vng.a.d = 1;
        }
        
        public void onActivityDestroyed(final Activity activity) {
            try {
                if (com.moat.analytics.mobile.vng.a.d != 3 && com.moat.analytics.mobile.vng.a.d != 5) {
                    if (com.moat.analytics.mobile.vng.a.e) {
                        a(false);
                    }
                    com.moat.analytics.mobile.vng.a.e = false;
                }
                com.moat.analytics.mobile.vng.a.d = 6;
                p.a(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (b(activity)) {
                    com.moat.analytics.mobile.vng.a.a = new WeakReference<Activity>(null);
                }
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        
        public void onActivityPaused(final Activity activity) {
            try {
                com.moat.analytics.mobile.vng.a.d = 4;
                if (b(activity)) {
                    com.moat.analytics.mobile.vng.a.a = new WeakReference<Activity>(null);
                }
                p.a(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        
        public void onActivityResumed(final Activity activity) {
            try {
                com.moat.analytics.mobile.vng.a.a = new WeakReference<Activity>(activity);
                com.moat.analytics.mobile.vng.a.d = 3;
                w.a().b();
                p.a(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((k)MoatAnalytics.getInstance()).b) {
                    f.a(activity);
                }
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            try {
                com.moat.analytics.mobile.vng.a.a = new WeakReference<Activity>(activity);
                com.moat.analytics.mobile.vng.a.d = 2;
                if (!com.moat.analytics.mobile.vng.a.e) {
                    a(true);
                }
                com.moat.analytics.mobile.vng.a.e = true;
                p.a(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        
        public void onActivityStopped(final Activity activity) {
            try {
                if (com.moat.analytics.mobile.vng.a.d != 3) {
                    com.moat.analytics.mobile.vng.a.e = false;
                    a(false);
                }
                com.moat.analytics.mobile.vng.a.d = 5;
                if (b(activity)) {
                    com.moat.analytics.mobile.vng.a.a = new WeakReference<Activity>(null);
                }
                p.a(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
    }
}
