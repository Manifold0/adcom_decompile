// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsw implements zzts
{
    private final /* synthetic */ int zzbnx;
    
    zzsw(final zzsu zzsu, final int zzbnx) {
        this.zzbnx = zzbnx;
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzxs != null) {
            zztt.zzxs.onAdFailedToLoad(this.zzbnx);
        }
    }
}
