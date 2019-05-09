// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.os.Bundle;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.Activity;
import java.lang.ref.WeakReference;

final class a
{
    static WeakReference<Activity> \u02ca;
    private static boolean \u02cb;
    private static boolean \u02ce;
    private static Application \u02cf;
    private static int \u0971;
    
    static {
        a.\u02cb = false;
        a.\u0971 = 0;
        a.\u02ce = false;
    }
    
    static void \u02ca(final Application \u02cf) {
        a.\u02cf = \u02cf;
        if (!a.\u02cb) {
            a.\u02cb = true;
            a.\u02cf.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new c());
        }
    }
    
    static Application \u02ce() {
        return a.\u02cf;
    }
    
    static /* synthetic */ boolean \u02ce(final Activity activity) {
        return a.\u02ca != null && a.\u02ca.get() == activity;
    }
    
    static final class c implements Application$ActivityLifecycleCallbacks
    {
        private static void \u02cb(final boolean b) {
            if (b) {
                b.\u02cf(3, "ActivityState", null, "App became visible");
                if (t.\u02cb().\u02cb == t.c.\u0971 && !((j)MoatAnalytics.getInstance()).\u02cf) {
                    k.\u02ce().\u02cb();
                }
            }
            else {
                b.\u02cf(3, "ActivityState", null, "App became invisible");
                if (t.\u02cb().\u02cb == t.c.\u0971 && !((j)MoatAnalytics.getInstance()).\u02cf) {
                    k.\u02ce().\u02ca();
                }
            }
        }
        
        public final void onActivityCreated(final Activity activity, final Bundle bundle) {
            a.\u0971 = 1;
        }
        
        public final void onActivityDestroyed(final Activity activity) {
            try {
                if (a.\u0971 != 3 && a.\u0971 != 5) {
                    if (a.\u02ce) {
                        \u02cb(false);
                    }
                    a.\u02ce = false;
                }
                a.\u0971 = 6;
                b.\u02cf(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (a.\u02ce(activity)) {
                    a.\u02ca = new WeakReference<Activity>(null);
                }
            }
            catch (Exception ex) {
                o.\u0971(ex);
            }
        }
        
        public final void onActivityPaused(final Activity activity) {
            try {
                a.\u0971 = 4;
                if (a.\u02ce(activity)) {
                    a.\u02ca = new WeakReference<Activity>(null);
                }
                b.\u02cf(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u0971(ex);
            }
        }
        
        public final void onActivityResumed(final Activity activity) {
            try {
                a.\u02ca = new WeakReference<Activity>(activity);
                a.\u0971 = 3;
                t.\u02cb().\u02cf();
                b.\u02cf(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((j)MoatAnalytics.getInstance()).\u02ce) {
                    e.\u02ca(activity);
                }
            }
            catch (Exception ex) {
                o.\u0971(ex);
            }
        }
        
        public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public final void onActivityStarted(final Activity activity) {
            try {
                a.\u02ca = new WeakReference<Activity>(activity);
                a.\u0971 = 2;
                if (!a.\u02ce) {
                    \u02cb(true);
                }
                a.\u02ce = true;
                b.\u02cf(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u0971(ex);
            }
        }
        
        public final void onActivityStopped(final Activity activity) {
            try {
                if (a.\u0971 != 3) {
                    a.\u02ce = false;
                    \u02cb(false);
                }
                a.\u0971 = 5;
                if (a.\u02ce(activity)) {
                    a.\u02ca = new WeakReference<Activity>(null);
                }
                b.\u02cf(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            }
            catch (Exception ex) {
                o.\u0971(ex);
            }
        }
    }
}
