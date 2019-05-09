package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcc extends zzcg {
    private final /* synthetic */ zzcb zzfi;

    zzcc(zzcb zzcb, GoogleApiClient googleApiClient) {
        this.zzfi = zzcb;
        super(zzcb, googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zzb(new zzce(this.zzfi, this, null));
    }
}
