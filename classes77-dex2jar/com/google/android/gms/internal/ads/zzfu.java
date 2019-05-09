// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.app.Application;
import android.app.Application$ActivityLifecycleCallbacks;

final class zzfu implements Application$ActivityLifecycleCallbacks
{
    private final Application zzagd;
    private final WeakReference<Application$ActivityLifecycleCallbacks> zzagv;
    private boolean zzagw;
    
    public zzfu(final Application zzagd, final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks) {
        this.zzagw = false;
        this.zzagv = new WeakReference<Application$ActivityLifecycleCallbacks>(application$ActivityLifecycleCallbacks);
        this.zzagd = zzagd;
    }
    
    private final void zza(final zzgc zzgc) {
        try {
            final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks = this.zzagv.get();
            if (application$ActivityLifecycleCallbacks != null) {
                zzgc.zza(application$ActivityLifecycleCallbacks);
                return;
            }
            if (!this.zzagw) {
                this.zzagd.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
                this.zzagw = true;
            }
        }
        catch (Exception ex) {
            zzakb.zzb("Error while dispatching lifecycle callback.", (Throwable)ex);
        }
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.zza(new zzfv(this, activity, bundle));
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        this.zza(new zzgb(this, activity));
    }
    
    public final void onActivityPaused(final Activity activity) {
        this.zza(new zzfy(this, activity));
    }
    
    public final void onActivityResumed(final Activity activity) {
        this.zza(new zzfx(this, activity));
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        this.zza(new zzga(this, activity, bundle));
    }
    
    public final void onActivityStarted(final Activity activity) {
        this.zza(new zzfw(this, activity));
    }
    
    public final void onActivityStopped(final Activity activity) {
        this.zza(new zzfz(this, activity));
    }
}
