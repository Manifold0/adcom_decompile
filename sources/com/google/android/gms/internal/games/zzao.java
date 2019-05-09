package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzao extends zzau {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ boolean zzjg;

    zzao(zzam zzam, GoogleApiClient googleApiClient, String str, boolean z) {
        this.zzbq = str;
        this.zzjg = z;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb((ResultHolder) this, this.zzbq, this.zzjg);
    }
}
