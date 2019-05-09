package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdr extends zzar {
    private final /* synthetic */ zzdp zzgo;

    zzdr(zzdp zzdp, GoogleApiClient googleApiClient) {
        this.zzgo = zzdp;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzex(this.zzgo.zzk), new zzdx(this));
    }
}
