// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.os.Bundle;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.app.Application;

final class c
{
    private static boolean \u02ca;
    private static Application \u02cb;
    private static boolean \u02ce;
    static WeakReference<Activity> \u02cf;
    private static int \u0971;
    
    static {
        c.\u02ce = false;
        c.\u0971 = 0;
        c.\u02ca = false;
    }
    
    static /* synthetic */ boolean \u02ca(final Activity activity) {
        return c.\u02cf != null && c.\u02cf.get() == activity;
    }
    
    static Application \u02cf() {
        return c.\u02cb;
    }
    
    static void \u0971(final Application \u02cb) {
        c.\u02cb = \u02cb;
        if (!c.\u02ce) {
            c.\u02ce = true;
            c.\u02cb.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new a());
        }
    }
    
    static final class a implements Application$ActivityLifecycleCallbacks
    {
        private static void \u0971(final boolean b) {
            if (b) {
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", null, "App became visible");
                if (t.\u02cf().\u02ce == t.a.\u02ce && !((f)MoatAnalytics.getInstance()).\u02cb) {
                    n.\u02cf().\u02cb();
                }
            }
            else {
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", null, "App became invisible");
                if (t.\u02cf().\u02ce == t.a.\u02ce && !((f)MoatAnalytics.getInstance()).\u02cb) {
                    n.\u02cf().\u0971();
                }
            }
        }
        
        public final void onActivityCreated(final Activity activity, final Bundle bundle) {
            c.\u0971 = 1;
        }
        
        public final void onActivityDestroyed(final Activity activity) {
            try {
                if (c.\u0971 != 3 && c.\u0971 != 5) {
                    if (c.\u02ca) {
                        \u0971(false);
                    }
                    c.\u02ca = false;
                }
                c.\u0971 = 6;
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (c.\u02ca(activity)) {
                    c.\u02cf = new WeakReference<Activity>(null);
                }
            }
            catch (Exception ex) {
                o.\u02ce(ex);
            }
        }
        
        public final void onActivityPaused(final Activity activity) {
            try {
                c.\u0971 = 4;
                if (c.\u02ca(activity)) {
                    c.\u02cf = new WeakReference<Activity>(null);
                }
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u02ce(ex);
            }
        }
        
        public final void onActivityResumed(final Activity activity) {
            try {
                c.\u02cf = new WeakReference<Activity>(activity);
                c.\u0971 = 3;
                t.\u02cf().\u02ce();
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((f)MoatAnalytics.getInstance()).\u02cf) {
                    e.\u02ce(activity);
                }
            }
            catch (Exception ex) {
                o.\u02ce(ex);
            }
        }
        
        public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public final void onActivityStarted(final Activity activity) {
            try {
                c.\u02cf = new WeakReference<Activity>(activity);
                c.\u0971 = 2;
                if (!c.\u02ca) {
                    \u0971(true);
                }
                c.\u02ca = true;
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u02ce(ex);
            }
        }
        
        public final void onActivityStopped(final Activity activity) {
            try {
                if (c.\u0971 != 3) {
                    c.\u02ca = false;
                    \u0971(false);
                }
                c.\u0971 = 5;
                if (c.\u02ca(activity)) {
                    c.\u02cf = new WeakReference<Activity>(null);
                }
                com.moat.analytics.mobile.cha.a.\u02cf(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u02ce(ex);
            }
        }
    }
}
