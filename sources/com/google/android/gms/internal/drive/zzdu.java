package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdu extends zzav {
    private final /* synthetic */ zzdp zzgo;

    zzdu(zzdp zzdp, GoogleApiClient googleApiClient) {
        this.zzgo = zzdp;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzab(this.zzgo.zzk), new zzgs(this));
    }
}
