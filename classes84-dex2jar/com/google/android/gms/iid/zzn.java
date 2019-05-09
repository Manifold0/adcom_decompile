// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class zzn extends BroadcastReceiver
{
    private /* synthetic */ zzl zzidh;
    
    zzn(final zzl zzidh) {
        this.zzidh = zzidh;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Received GSF callback via dynamic receiver");
        }
        this.zzidh.zzk(intent);
    }
}
