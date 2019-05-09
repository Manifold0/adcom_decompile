// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;
import android.os.Process;
import android.os.Binder;

public final class zzf extends Binder
{
    private final zzb zzicc;
    
    zzf(final zzb zzicc) {
        this.zzicc = zzicc;
    }
    
    public final void zza(final zzd zzd) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "service received new intent via bind strategy");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "intent being queued for bg execution");
        }
        this.zzicc.zzibs.execute(new zzg(this, zzd));
    }
}
