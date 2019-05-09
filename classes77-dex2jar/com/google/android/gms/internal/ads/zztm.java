// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztm implements zzts
{
    zztm(final zzti zzti) {
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.onRewardedVideoAdClosed();
        }
    }
}
