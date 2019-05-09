// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.internal.auth.zzad;

final class zze extends zzb<byte[]>
{
    private final /* synthetic */ zzad zzap;
    
    zze(final AccountTransferClient accountTransferClient, final zzad zzap) {
        this.zzap = zzap;
        super(null);
    }
    
    @Override
    protected final void zza(final zzz zzz) throws RemoteException {
        zzz.zza(new zzf(this, this), this.zzap);
    }
}
