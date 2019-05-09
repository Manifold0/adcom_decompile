package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;

final class zzj extends zzr {
    private final /* synthetic */ String val$id;

    zzj(zzf zzf, String str, GoogleApiClient googleApiClient, String str2) {
        this.val$id = str2;
        super(str, googleApiClient);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzb(null, this.val$id);
    }
}
