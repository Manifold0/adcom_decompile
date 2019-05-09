package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzx extends zzz {
    private final /* synthetic */ boolean zzjg;

    zzx(zzv zzv, GoogleApiClient googleApiClient, boolean z) {
        this.zzjg = z;
        super(googleApiClient);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzd((ResultHolder) this, this.zzjg);
    }
}
