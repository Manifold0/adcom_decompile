package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzah;
import com.google.android.gms.internal.auth.zzz;

final class zzi extends zzc {
    private final /* synthetic */ zzah zzat;

    zzi(AccountTransferClient accountTransferClient, zzah zzah) {
        this.zzat = zzah;
        super();
    }

    protected final void zza(zzz zzz) throws RemoteException {
        zzz.zza(this.zzax, this.zzat);
    }
}
