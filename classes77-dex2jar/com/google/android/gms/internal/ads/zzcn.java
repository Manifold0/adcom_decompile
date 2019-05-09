// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.app.Application;
import android.app.Application$ActivityLifecycleCallbacks;

final class zzcn implements Application$ActivityLifecycleCallbacks
{
    private final Application zzrk;
    private final WeakReference<Application$ActivityLifecycleCallbacks> zzrl;
    private boolean zzrm;
    
    public zzcn(final Application zzrk, final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks) {
        this.zzrm = false;
        this.zzrl = new WeakReference<Application$ActivityLifecycleCallbacks>(application$ActivityLifecycleCallbacks);
        this.zzrk = zzrk;
    }
    
    private final void zza(final zzcv zzcv) {
        try {
            final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks = this.zzrl.get();
            if (application$ActivityLifecycleCallbacks != null) {
                zzcv.zza(application$ActivityLifecycleCallbacks);
                return;
            }
            if (!this.zzrm) {
                this.zzrk.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
                this.zzrm = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.zza(new zzco(this, activity, bundle));
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        this.zza(new zzcu(this, activity));
    }
    
    public final void onActivityPaused(final Activity activity) {
        this.zza(new zzcr(this, activity));
    }
    
    public final void onActivityResumed(final Activity activity) {
        this.zza(new zzcq(this, activity));
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        this.zza(new zzct(this, activity, bundle));
    }
    
    public final void onActivityStarted(final Activity activity) {
        this.zza(new zzcp(this, activity));
    }
    
    public final void onActivityStopped(final Activity activity) {
        this.zza(new zzcs(this, activity));
    }
}
