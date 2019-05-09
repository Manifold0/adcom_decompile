package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzcc extends zzcg {
    private final /* synthetic */ String[] zzjx;

    zzcc(zzca zzca, GoogleApiClient googleApiClient, String[] strArr) {
        this.zzjx = strArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb((ResultHolder) this, this.zzjx);
    }
}
