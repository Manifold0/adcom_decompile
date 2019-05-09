// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztr implements Runnable
{
    private final /* synthetic */ zzts zzbob;
    private final /* synthetic */ zztt zzboc;
    
    zztr(final zzst zzst, final zzts zzbob, final zztt zzboc) {
        this.zzbob = zzbob;
        this.zzboc = zzboc;
    }
    
    @Override
    public final void run() {
        try {
            this.zzbob.zzb(this.zzboc);
        }
        catch (RemoteException ex) {
            zzakb.zzc("Could not propagate interstitial ad event.", (Throwable)ex);
        }
    }
}
