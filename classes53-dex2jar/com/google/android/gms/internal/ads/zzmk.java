// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmk implements Runnable
{
    private final /* synthetic */ zzmj zzatk;
    
    zzmk(final zzmj zzatk) {
        this.zzatk = zzatk;
    }
    
    @Override
    public final void run() {
        if (this.zzatk.zzxs == null) {
            return;
        }
        try {
            this.zzatk.zzxs.onAdFailedToLoad(1);
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not notify onAdFailedToLoad event.", (Throwable)ex);
        }
    }
}
