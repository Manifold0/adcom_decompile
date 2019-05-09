package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzg extends zzp {
    private final /* synthetic */ boolean zzjg;

    zzg(zzf zzf, GoogleApiClient googleApiClient, boolean z) {
        this.zzjg = z;
        super(googleApiClient);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzc((ResultHolder) this, this.zzjg);
    }
}
