package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbq extends zzbv {
    private final /* synthetic */ String zzjt;
    private final /* synthetic */ String zzju;

    zzbq(zzbo zzbo, GoogleApiClient googleApiClient, String str, String str2) {
        this.zzjt = str;
        this.zzju = str2;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb((ResultHolder) this, this.zzjt, this.zzju);
    }
}
