// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class zzamr extends BroadcastReceiver
{
    private final /* synthetic */ zzamq zzcuf;
    
    zzamr(final zzamq zzcuf) {
        this.zzcuf = zzcuf;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        this.zzcuf.zzc(context, intent);
    }
}
