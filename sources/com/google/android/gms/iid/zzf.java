package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzf extends Binder {
    private final zzb zzicc;

    zzf(zzb zzb) {
        this.zzicc = zzb;
    }

    public final void zza(zzd zzd) {
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
