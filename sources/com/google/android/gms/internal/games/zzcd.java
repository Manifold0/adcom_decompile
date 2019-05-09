package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzcd extends zzce {
    private final /* synthetic */ int zzjl;
    private final /* synthetic */ int zzjy;
    private final /* synthetic */ int zzjz;

    zzcd(zzca zzca, GoogleApiClient googleApiClient, int i, int i2, int i3) {
        this.zzjy = i;
        this.zzjz = i2;
        this.zzjl = i3;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjy, this.zzjz, this.zzjl);
    }
}
