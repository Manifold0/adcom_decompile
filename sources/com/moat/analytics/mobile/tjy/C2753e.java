package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.e */
class C2753e implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    final /* synthetic */ C2751c f6712a;

    private C2753e(C2751c c2751c) {
        this.f6712a = c2751c;
    }

    /* renamed from: a */
    private boolean m6931a(Activity activity) {
        Activity activity2 = (Activity) this.f6712a.f6708b.get();
        return activity2 != null && activity2.equals(activity);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        try {
            if (this.f6712a.f6710d.mo6105b()) {
                Log.d("MoatActivityState", "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
            }
            if (m6931a(activity)) {
                this.f6712a.f6711e = false;
                ((Application) this.f6712a.f6707a.get()).unregisterActivityLifecycleCallbacks(this);
            }
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.f6712a.f6710d.mo6105b()) {
            Log.d("MoatActivityState", "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m6931a(activity)) {
            this.f6712a.f6711e = true;
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.f6712a.f6710d.mo6105b()) {
            Log.d("MoatActivityState", "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m6931a(activity)) {
            this.f6712a.f6711e = false;
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.f6712a.f6710d.mo6105b()) {
            Log.d("MoatActivityState", "Activity started: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m6931a(activity)) {
            this.f6712a.f6711e = false;
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.f6712a.f6710d.mo6105b()) {
            Log.d("MoatActivityState", "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m6931a(activity)) {
            this.f6712a.f6711e = true;
        }
    }
}
