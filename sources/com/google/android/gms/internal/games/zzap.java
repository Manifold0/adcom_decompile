package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzap extends zzaw {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ int zzjm;
    private final /* synthetic */ int zzjn;

    zzap(zzam zzam, GoogleApiClient googleApiClient, String str, int i, int i2) {
        this.zzbq = str;
        this.zzjm = i;
        this.zzjn = i2;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, null, this.zzbq, this.zzjm, this.zzjn);
    }
}
