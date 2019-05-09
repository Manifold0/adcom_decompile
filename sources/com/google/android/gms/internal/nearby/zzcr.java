package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcr extends zzcy {
    zzcr(zzca zzca, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).stopDiscovery();
    }
}
