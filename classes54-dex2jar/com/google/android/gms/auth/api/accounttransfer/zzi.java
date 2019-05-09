// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.internal.auth.zzah;

final class zzi extends zzc
{
    private final /* synthetic */ zzah zzat;
    
    zzi(final AccountTransferClient accountTransferClient, final zzah zzat) {
        this.zzat = zzat;
        super(null);
    }
    
    @Override
    protected final void zza(final zzz zzz) throws RemoteException {
        zzz.zza(this.zzax, this.zzat);
    }
}
