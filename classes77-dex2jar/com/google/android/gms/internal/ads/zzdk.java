// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class zzdk extends BroadcastReceiver
{
    private final /* synthetic */ zzdi zztj;
    
    zzdk(final zzdi zztj) {
        this.zztj = zztj;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        this.zztj.zzaq();
    }
}
