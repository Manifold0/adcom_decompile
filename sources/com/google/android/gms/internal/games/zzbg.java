package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbg extends zzbm {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String zzjr;

    zzbg(zzbe zzbe, GoogleApiClient googleApiClient, String str, boolean z) {
        this.zzjr = str;
        this.zzjg = z;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjr, this.zzjg);
    }
}
