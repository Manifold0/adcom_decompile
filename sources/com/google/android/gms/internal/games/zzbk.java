package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzbk extends zzbm {
    private final /* synthetic */ int zzjs;

    zzbk(zzbe zzbe, GoogleApiClient googleApiClient, int i) {
        this.zzjs = i;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, "played_with", this.zzjs, true, false);
    }
}
