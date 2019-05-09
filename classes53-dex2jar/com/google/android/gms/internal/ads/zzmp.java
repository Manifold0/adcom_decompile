// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmp implements Runnable
{
    private final /* synthetic */ zzmo zzatm;
    
    zzmp(final zzmo zzatm) {
        this.zzatm = zzatm;
    }
    
    @Override
    public final void run() {
        if (this.zzatm.zzatl == null) {
            return;
        }
        try {
            this.zzatm.zzatl.onRewardedVideoAdFailedToLoad(1);
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", (Throwable)ex);
        }
    }
}
