// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsy implements zzts
{
    zzsy(final zzsu zzsu) {
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzxs != null) {
            zztt.zzxs.onAdLoaded();
        }
    }
}
