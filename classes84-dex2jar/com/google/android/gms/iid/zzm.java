// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzm extends Handler
{
    private /* synthetic */ zzl zzidh;
    
    zzm(final zzl zzidh, final Looper looper) {
        this.zzidh = zzidh;
        super(looper);
    }
    
    public final void handleMessage(final Message message) {
        this.zzidh.zzc(message);
    }
}
