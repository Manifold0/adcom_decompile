package com.google.android.gms.nearby.messages.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

@TargetApi(14)
final class zzaj implements ActivityLifecycleCallbacks {
    private final Activity zzhm;
    private final zzah zzhn;

    private zzaj(Activity activity, zzah zzah) {
        this.zzhm = activity;
        this.zzhn = zzah;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (activity == this.zzhm) {
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", new Object[]{activity.getPackageName()}));
            }
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
            try {
                this.zzhn.zzf(1);
            } catch (RemoteException e) {
                if (Log.isLoggable("NearbyMessagesClient", 2)) {
                    Log.v("NearbyMessagesClient", String.format("Failed to emit ACTIVITY_STOPPED from ClientLifecycleSafetyNet for Activity %s: %s", new Object[]{activity.getPackageName(), e}));
                }
            }
        }
    }
}
