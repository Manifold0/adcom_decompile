package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzay extends zzav {
    private final /* synthetic */ zzee zzej;
    private final /* synthetic */ zzgm zzek;

    zzay(zzaw zzaw, GoogleApiClient googleApiClient, zzgm zzgm, zzee zzee) {
        this.zzek = zzgm;
        this.zzej = zzee;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(this.zzek, this.zzej, null, new zzgs(this));
    }
}
