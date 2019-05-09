// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.os.Message;
import android.os.Looper;
import com.google.android.gms.internal.base.zap;

final class zabb extends zap
{
    private final /* synthetic */ zaaw zahh;
    
    zabb(final zaaw zahh, final Looper looper) {
        this.zahh = zahh;
        super(looper);
    }
    
    public final void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GoogleApiClientImpl", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
            }
            case 1: {
                this.zahh.zaav();
            }
            case 2: {
                this.zahh.resume();
            }
        }
    }
}
