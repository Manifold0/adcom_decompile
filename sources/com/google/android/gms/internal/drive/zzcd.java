package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcd extends zzav {
    private final /* synthetic */ zzei zzfj;

    zzcd(zzcb zzcb, GoogleApiClient googleApiClient, zzei zzei) {
        this.zzfj = zzei;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgo(this.zzfj), new zzgs(this));
    }
}
