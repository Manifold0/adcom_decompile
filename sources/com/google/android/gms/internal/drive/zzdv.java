package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdv extends zzav {
    private final /* synthetic */ zzdp zzgo;

    zzdv(zzdp zzdp, GoogleApiClient googleApiClient) {
        this.zzgo = zzdp;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgv(this.zzgo.zzk), new zzgs(this));
    }
}
