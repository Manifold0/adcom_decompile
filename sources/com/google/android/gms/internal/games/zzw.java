package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzw extends zzz {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjk;

    zzw(zzv zzv, GoogleApiClient googleApiClient, boolean z, String[] strArr) {
        this.zzjg = z;
        this.zzjk = strArr;
        super(googleApiClient);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjg, this.zzjk);
    }
}
