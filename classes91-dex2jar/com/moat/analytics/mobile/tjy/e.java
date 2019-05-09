// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;
import android.app.Application;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

class e implements Application$ActivityLifecycleCallbacks
{
    final /* synthetic */ c a;
    
    private e(final c a) {
        this.a = a;
    }
    
    private boolean a(final Activity activity) {
        final Activity activity2 = (Activity)this.a.b.get();
        return activity2 != null && activity2.equals(activity);
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
        try {
            if (this.a.d.b()) {
                Log.d("MoatActivityState", "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
            }
            if (this.a(activity)) {
                this.a.e = false;
                ((Application)this.a.a.get()).unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
            }
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
    
    public void onActivityPaused(final Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (this.a(activity)) {
            this.a.e = true;
        }
    }
    
    public void onActivityResumed(final Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (this.a(activity)) {
            this.a.e = false;
        }
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity started: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (this.a(activity)) {
            this.a.e = false;
        }
    }
    
    public void onActivityStopped(final Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (this.a(activity)) {
            this.a.e = true;
        }
    }
}
