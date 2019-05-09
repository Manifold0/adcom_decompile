// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztn implements zzts
{
    private final /* synthetic */ zzagu zzboa;
    
    zztn(final zzti zzti, final zzagu zzboa) {
        this.zzboa = zzboa;
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.zza(this.zzboa);
        }
    }
}
