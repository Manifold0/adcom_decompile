// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

final class zzbc implements Application$ActivityLifecycleCallbacks
{
    private final Activity zzhm;
    private final zzak zzig;
    
    private zzbc(final Activity zzhm, final zzak zzig) {
        this.zzhm = zzhm;
        this.zzig = zzig;
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        if (activity == this.zzhm) {
            Log.v("NearbyMessages", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", activity.getPackageName()));
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
        if (activity == this.zzhm) {
            zzak.zza(this.zzig, 1);
        }
    }
}
