package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzde extends zzdo {
    private final /* synthetic */ String zzew;

    zzde(zzdb zzdb, GoogleApiClient googleApiClient, String str) {
        this.zzew = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzc((ResultHolder) this, this.zzew);
    }
}
