// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.base;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

public class zap extends Handler
{
    private static volatile zaq zarr;
    
    static {
        zap.zarr = null;
    }
    
    public zap() {
    }
    
    public zap(final Looper looper) {
        super(looper);
    }
    
    public zap(final Looper looper, final Handler$Callback handler$Callback) {
        super(looper, handler$Callback);
    }
    
    public final void dispatchMessage(final Message message) {
        super.dispatchMessage(message);
    }
}
