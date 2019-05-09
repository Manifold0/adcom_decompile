package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzce extends zzcy {
    private final /* synthetic */ long zzcx;

    zzce(zzca zzca, GoogleApiClient googleApiClient, long j) {
        this.zzcx = j;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, this.zzcx);
    }
}
