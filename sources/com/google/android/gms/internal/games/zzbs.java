package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbs extends zzbx {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjw;

    zzbs(zzbo zzbo, GoogleApiClient googleApiClient, boolean z, String[] strArr) {
        this.zzjg = z;
        this.zzjw = strArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb((ResultHolder) this, this.zzjg, this.zzjw);
    }
}
