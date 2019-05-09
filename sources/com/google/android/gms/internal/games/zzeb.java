package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzeb extends zzec {
    private final /* synthetic */ int zzkp;

    zzeb(zzdy zzdy, GoogleApiClient googleApiClient, int i) {
        this.zzkp = i;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb((ResultHolder) this, this.zzkp);
    }
}
