package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbh extends zzbm {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjs;

    zzbh(zzbe zzbe, GoogleApiClient googleApiClient, int i, boolean z) {
        this.zzjs = i;
        this.zzjg = z;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjs, false, this.zzjg);
    }
}
