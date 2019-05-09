package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzdk extends zzdm {
    private final /* synthetic */ String zzew;

    zzdk(zzdb zzdb, String str, GoogleApiClient googleApiClient, String str2) {
        this.zzew = str2;
        super(str, googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzf((ResultHolder) this, this.zzew);
    }
}
