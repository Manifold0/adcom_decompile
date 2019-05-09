// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.common;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

public class zze extends Handler
{
    private static volatile zzf zziu;
    
    static {
        zze.zziu = null;
    }
    
    public zze() {
    }
    
    public zze(final Looper looper) {
        super(looper);
    }
    
    public zze(final Looper looper, final Handler$Callback handler$Callback) {
        super(looper, handler$Callback);
    }
    
    public final void dispatchMessage(final Message message) {
        super.dispatchMessage(message);
    }
}
