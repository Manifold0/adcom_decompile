// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztf implements zzts
{
    private final /* synthetic */ zzoa zzbnz;
    
    zztf(final zzte zzte, final zzoa zzbnz) {
        this.zzbnz = zzbnz;
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzbof != null) {
            zztt.zzbof.zza(this.zzbnz);
        }
    }
}
