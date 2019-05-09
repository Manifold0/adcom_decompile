// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.content.IntentFilter;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import javax.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.BroadcastReceiver;

@VisibleForTesting
final class zzaz extends BroadcastReceiver
{
    @Nullable
    private zzay zzdk;
    
    public zzaz(final zzay zzdk) {
        this.zzdk = zzdk;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        if (this.zzdk != null && this.zzdk.zzao()) {
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza(this.zzdk, 0L);
            this.zzdk.getContext().unregisterReceiver((BroadcastReceiver)this);
            this.zzdk = null;
        }
    }
    
    public final void zzap() {
        if (FirebaseInstanceId.zzl()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdk.getContext().registerReceiver((BroadcastReceiver)this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
