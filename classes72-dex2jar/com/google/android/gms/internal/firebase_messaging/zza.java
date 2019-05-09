// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.firebase_messaging;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

public class zza extends Handler
{
    private static volatile zzb zza;
    
    static {
        com.google.android.gms.internal.firebase_messaging.zza.zza = null;
    }
    
    public zza() {
    }
    
    public zza(final Looper looper) {
        super(looper);
    }
    
    public zza(final Looper looper, final Handler$Callback handler$Callback) {
        super(looper, handler$Callback);
    }
    
    public final void dispatchMessage(final Message message) {
        super.dispatchMessage(message);
    }
}
