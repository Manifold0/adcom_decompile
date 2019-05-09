package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.nearby.zzgy;

final class zzbr extends zzbv {
    zzbr(zzbi zzbi, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzah zzah = (zzah) anyClient;
        ((zzs) zzah.getService()).zza(new zzh(new zzgy(zzah())));
    }
}
