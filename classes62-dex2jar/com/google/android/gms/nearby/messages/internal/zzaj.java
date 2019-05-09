// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;

@TargetApi(14)
final class zzaj implements Application$ActivityLifecycleCallbacks
{
    private final Activity zzhm;
    private final zzah zzhn;
    
    private zzaj(final Activity zzhm, final zzah zzhn) {
        this.zzhm = zzhm;
        this.zzhn = zzhn;
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        if (activity == this.zzhm) {
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", activity.getPackageName()));
            }
            activity.getApplication().unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
        }
    }
    
    public final void onActivityPaused(final Activity activity) {
    }
    
    public final void onActivityResumed(final Activity activity) {
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityStarted(final Activity activity) {
    }
    
    public final void onActivityStopped(final Activity activity) {
        if (activity != this.zzhm) {
            return;
        }
        try {
            this.zzhn.zzf(1);
        }
        catch (RemoteException ex) {
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Failed to emit ACTIVITY_STOPPED from ClientLifecycleSafetyNet for Activity %s: %s", activity.getPackageName(), ex));
            }
        }
    }
}
