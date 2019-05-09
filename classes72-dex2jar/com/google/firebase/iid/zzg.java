// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable
{
    private final /* synthetic */ zzd zzv;
    private final /* synthetic */ zzf zzw;
    
    zzg(final zzf zzw, final zzd zzv) {
        this.zzw = zzw;
        this.zzv = zzv;
    }
    
    @Override
    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzw.zzu.zzd(this.zzv.intent);
        this.zzv.finish();
    }
}
