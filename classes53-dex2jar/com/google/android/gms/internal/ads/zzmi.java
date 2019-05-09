// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmi implements Runnable
{
    private final /* synthetic */ zzmh zzatj;
    
    zzmi(final zzmh zzatj) {
        this.zzatj = zzatj;
    }
    
    @Override
    public final void run() {
        if (this.zzatj.zzati.zzxs == null) {
            return;
        }
        try {
            this.zzatj.zzati.zzxs.onAdFailedToLoad(1);
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not notify onAdFailedToLoad event.", (Throwable)ex);
        }
    }
}
