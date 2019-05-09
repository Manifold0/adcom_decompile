package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbr extends zzbx {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjl;
    private final /* synthetic */ int[] zzjv;

    zzbr(zzbo zzbo, GoogleApiClient googleApiClient, int[] iArr, int i, boolean z) {
        this.zzjv = iArr;
        this.zzjl = i;
        this.zzjg = z;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjv, this.zzjl, this.zzjg);
    }
}
