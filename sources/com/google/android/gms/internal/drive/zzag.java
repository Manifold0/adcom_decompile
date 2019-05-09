package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.query.Query;

final class zzag extends zzar {
    private final /* synthetic */ Query zzds;

    zzag(zzaf zzaf, GoogleApiClient googleApiClient, Query query) {
        this.zzds = query;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgk(this.zzds), new zzas(this));
    }
}
