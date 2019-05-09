// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.internal.auth.zzaf;

final class zzd extends zzc
{
    private final /* synthetic */ zzaf zzao;
    
    zzd(final AccountTransferClient accountTransferClient, final zzaf zzao) {
        this.zzao = zzao;
        super(null);
    }
    
    @Override
    protected final void zza(final zzz zzz) throws RemoteException {
        zzz.zza(this.zzax, this.zzao);
    }
}
