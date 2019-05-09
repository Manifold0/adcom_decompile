// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class zzakp extends BroadcastReceiver
{
    private final /* synthetic */ zzakk zzcru;
    
    private zzakp(final zzakk zzcru) {
        this.zzcru = zzcru;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.zzcru.zzcrn = true;
        }
        else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.zzcru.zzcrn = false;
        }
    }
}
