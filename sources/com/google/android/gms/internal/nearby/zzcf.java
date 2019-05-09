package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcf extends zzcy {
    private final /* synthetic */ String zzcv;

    zzcf(zzca zzca, GoogleApiClient googleApiClient, String str) {
        this.zzcv = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).disconnectFromEndpoint(this.zzcv);
    }
}
