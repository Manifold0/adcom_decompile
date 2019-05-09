// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.a;

import android.os.Bundle;
import android.app.Application;
import android.support.annotation.Nullable;
import android.os.Build$VERSION;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.content.Context;
import android.app.Application$ActivityLifecycleCallbacks;

public class b implements Application$ActivityLifecycleCallbacks
{
    private static Context a;
    private static WeakReference<Activity> b;
    
    static {
        com.facebook.ads.internal.w.a.b.b = new WeakReference<Activity>(null);
    }
    
    @Nullable
    public static Activity a() {
        while (true) {
            while (true) {
                synchronized (b.class) {
                    final Activity activity = com.facebook.ads.internal.w.a.b.b.get();
                    Activity a = null;
                    if (activity != null) {
                        if (Build$VERSION.SDK_INT >= 28) {
                            final int n = 0;
                            if (n != 0) {
                                a = com.facebook.ads.internal.w.a.a.a();
                            }
                            if (com.facebook.ads.internal.w.a.b.a != null && n != 0 && activity != a) {
                                com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.w.a.b.a, "act_util", com.facebook.ads.internal.w.h.b.Z, new Exception("Activity discrepancies res: " + activity + ", ref: " + a));
                            }
                            if (activity != null) {
                                a = activity;
                            }
                            return a;
                        }
                    }
                }
                final int n = 1;
                continue;
            }
        }
    }
    
    public static void a(final Context a) {
        synchronized (b.class) {
            com.facebook.ads.internal.w.a.b.a = a;
            if (com.facebook.ads.internal.w.a.b.a instanceof Application) {
                ((Application)com.facebook.ads.internal.w.a.b.a).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new b());
            }
            else {
                com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.w.a.b.a, "api", com.facebook.ads.internal.w.h.b.o, new Exception("AppContext is not Application."));
            }
        }
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
    }
    
    public void onActivityPaused(final Activity activity) {
        com.facebook.ads.internal.w.a.b.b = new WeakReference<Activity>(null);
    }
    
    public void onActivityResumed(final Activity activity) {
        com.facebook.ads.internal.w.a.b.b = new WeakReference<Activity>(activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
    }
    
    public void onActivityStopped(final Activity activity) {
    }
}
