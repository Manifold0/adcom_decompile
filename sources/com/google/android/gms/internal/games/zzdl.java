package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzdl extends zzdu {
    private final /* synthetic */ int zzkm;
    private final /* synthetic */ int[] zzkn;

    zzdl(zzdb zzdb, GoogleApiClient googleApiClient, int i, int[] iArr) {
        this.zzkm = i;
        this.zzkn = iArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzkm, this.zzkn);
    }
}
