// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

@zzadh
public final class zzakc extends Handler
{
    public zzakc(final Looper looper) {
        super(looper);
    }
    
    public final void dispatchMessage(final Message message) {
        try {
            super.dispatchMessage(message);
        }
        catch (Throwable t) {
            zzbv.zzek();
            zzakk.zza(zzbv.zzeo().getApplicationContext(), t);
            throw t;
        }
    }
    
    public final void handleMessage(final Message message) {
        try {
            super.handleMessage(message);
        }
        catch (Exception ex) {
            zzbv.zzeo().zza(ex, "AdMobHandler.handleMessage");
        }
    }
}
