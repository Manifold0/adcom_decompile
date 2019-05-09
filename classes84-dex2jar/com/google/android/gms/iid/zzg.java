// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;

final class zzg implements Runnable
{
    private /* synthetic */ zzd zzicd;
    private /* synthetic */ zzf zzice;
    
    zzg(final zzf zzice, final zzd zzicd) {
        this.zzice = zzice;
        this.zzicd = zzicd;
    }
    
    @Override
    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzice.zzicc.handleIntent(this.zzicd.intent);
        this.zzicd.finish();
    }
}
