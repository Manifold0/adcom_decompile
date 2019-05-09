// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Message;
import android.os.Looper;
import com.google.android.gms.internal.firebase_messaging.zza;

final class zzau extends zza
{
    private final /* synthetic */ zzat zzcw;
    
    zzau(final zzat zzcw, final Looper looper) {
        this.zzcw = zzcw;
        super(looper);
    }
    
    public final void handleMessage(final Message message) {
        this.zzcw.zzb(message);
    }
}
