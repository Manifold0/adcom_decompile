// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztp implements zzts
{
    private final /* synthetic */ int zzbnx;
    
    zztp(final zzti zzti, final int zzbnx) {
        this.zzbnx = zzbnx;
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.onRewardedVideoAdFailedToLoad(this.zzbnx);
        }
    }
}
