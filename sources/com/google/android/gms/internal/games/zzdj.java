package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzdj extends zzdq {
    private final /* synthetic */ String zzew;
    private final /* synthetic */ String zzkk;

    zzdj(zzdb zzdb, GoogleApiClient googleApiClient, String str, String str2) {
        this.zzew = str;
        this.zzkk = str2;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzew, this.zzkk);
    }
}
