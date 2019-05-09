package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzbs extends zzbv {
    private final /* synthetic */ ListenerHolder zzik;

    zzbs(zzbi zzbi, GoogleApiClient googleApiClient, ListenerHolder listenerHolder) {
        this.zzik = listenerHolder;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zzb(zzah(), this.zzik);
    }
}
