package com.google.android.gms.nearby.messages.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;

final class zzbc implements ActivityLifecycleCallbacks {
    private final Activity zzhm;
    private final zzak zzig;

    private zzbc(Activity activity, zzak zzak) {
        this.zzhm = activity;
        this.zzig = zzak;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (activity == this.zzhm) {
            Log.v("NearbyMessages", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", new Object[]{activity.getPackageName()}));
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
        if (activity == this.zzhm) {
            this.zzig.zzf(1);
        }
    }
}
