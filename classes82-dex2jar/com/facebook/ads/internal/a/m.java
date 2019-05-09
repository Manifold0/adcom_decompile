// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import android.os.Bundle;
import java.lang.ref.WeakReference;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;
import java.util.Map;
import java.util.HashMap;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.app.Application;
import com.facebook.ads.internal.s.c;

public class m
{
    private final c a;
    @Nullable
    private Application b;
    @Nullable
    private a c;
    private long d;
    @Nullable
    private String e;
    @Nullable
    private com.facebook.ads.internal.a.a f;
    
    private m(final c a, final Activity activity, final int n) {
        this.d = 0L;
        this.e = null;
        this.f = null;
        this.a = a;
        this.b = activity.getApplication();
        this.c = new a(activity, this);
    }
    
    public static m a(final c c, final Activity activity) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (activity != null && sdk_INT >= 14) {
            return new m(c, activity, sdk_INT);
        }
        return null;
    }
    
    private void a(final String s, final long n, final long n2, @Nullable final com.facebook.ads.internal.a.a a) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("leave_time", Long.toString(n));
        hashMap.put("back_time", Long.toString(n2));
        if (a != null) {
            hashMap.put("outcome", a.name());
        }
        this.a.m(s, hashMap);
    }
    
    @TargetApi(14)
    public void a() {
        this.a(this.e, this.d, System.currentTimeMillis(), this.f);
        if (this.b != null && this.c != null) {
            this.b.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.c);
            this.c = null;
            this.b = null;
        }
    }
    
    public void a(@Nullable final com.facebook.ads.internal.a.a f) {
        this.f = f;
    }
    
    @TargetApi(14)
    public void a(final String e) {
        this.e = e;
        if (this.c != null && this.b != null) {
            this.d = System.currentTimeMillis();
            this.b.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.c);
            return;
        }
        this.a(e, -1L, -1L, com.facebook.ads.internal.a.a.b);
    }
    
    @TargetApi(14)
    private static class a implements Application$ActivityLifecycleCallbacks
    {
        private final WeakReference<Activity> a;
        @Nullable
        private m b;
        
        public a(final Activity activity, final m b) {
            this.a = new WeakReference<Activity>(activity);
            this.b = b;
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
        }
        
        public void onActivityResumed(final Activity activity) {
            if (this.b != null) {
                final Activity activity2 = this.a.get();
                if (activity2 == null || (activity2 != null && activity.equals(activity2))) {
                    this.b.a();
                    this.b = null;
                }
            }
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
        }
        
        public void onActivityStopped(final Activity activity) {
        }
    }
}
