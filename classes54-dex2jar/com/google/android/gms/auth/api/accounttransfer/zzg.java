// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.internal.auth.zzv;

final class zzg extends zzb<DeviceMetaData>
{
    private final /* synthetic */ zzv zzar;
    
    zzg(final AccountTransferClient accountTransferClient, final zzv zzar) {
        this.zzar = zzar;
        super(null);
    }
    
    @Override
    protected final void zza(final zzz zzz) throws RemoteException {
        zzz.zza(new zzh(this, this), this.zzar);
    }
}
