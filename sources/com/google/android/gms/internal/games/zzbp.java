package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;

final class zzbp extends zzbt {
    private final /* synthetic */ String zzjt;

    zzbp(zzbo zzbo, GoogleApiClient googleApiClient, String str) {
        this.zzjt = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzh(this, this.zzjt);
    }
}
