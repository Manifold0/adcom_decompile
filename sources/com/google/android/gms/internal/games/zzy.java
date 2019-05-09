package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;

final class zzy extends zzab {
    private final /* synthetic */ String zzk;
    private final /* synthetic */ int zzl;

    zzy(zzv zzv, GoogleApiClient googleApiClient, String str, int i) {
        this.zzk = str;
        this.zzl = i;
        super(googleApiClient);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza(this.zzk, this.zzl);
    }
}
