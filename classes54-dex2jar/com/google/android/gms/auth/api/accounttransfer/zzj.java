// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.internal.auth.zzab;

final class zzj extends zzc
{
    private final /* synthetic */ zzab zzau;
    
    zzj(final AccountTransferClient accountTransferClient, final zzab zzau) {
        this.zzau = zzau;
        super(null);
    }
    
    @Override
    protected final void zza(final zzz zzz) throws RemoteException {
        zzz.zza(this.zzax, this.zzau);
    }
}
