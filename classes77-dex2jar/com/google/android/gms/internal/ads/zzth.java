// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzth implements zzts
{
    zzth(final zztg zztg) {
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzbog != null) {
            zztt.zzbog.onAdClicked();
        }
    }
}
