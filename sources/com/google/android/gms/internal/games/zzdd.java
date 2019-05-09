package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;

final class zzdd extends zzds {
    private final /* synthetic */ String zzew;

    zzdd(zzdb zzdb, GoogleApiClient googleApiClient, String str) {
        this.zzew = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzg(this, this.zzew);
    }
}
