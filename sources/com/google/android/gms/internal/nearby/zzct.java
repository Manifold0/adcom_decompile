package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzct extends zzcy {
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ ListenerHolder zzdh;

    zzct(zzca zzca, GoogleApiClient googleApiClient, String str, ListenerHolder listenerHolder) {
        this.zzcv = str;
        this.zzdh = listenerHolder;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza(this, this.zzcv, this.zzdh);
    }
}
