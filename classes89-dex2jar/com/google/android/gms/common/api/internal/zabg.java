// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.os.Message;
import android.os.Looper;
import com.google.android.gms.internal.base.zap;

final class zabg extends zap
{
    private final /* synthetic */ zabe zahv;
    
    zabg(final zabe zahv, final Looper looper) {
        this.zahv = zahv;
        super(looper);
    }
    
    public final void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
            }
            case 1: {
                ((zabf)message.obj).zac(this.zahv);
            }
            case 2: {
                throw (RuntimeException)message.obj;
            }
        }
    }
}
