package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzbn extends zzbv {
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ zzbw zzio;
    private final /* synthetic */ SubscribeOptions zzip;

    zzbn(zzbi zzbi, GoogleApiClient googleApiClient, ListenerHolder listenerHolder, zzbw zzbw, SubscribeOptions subscribeOptions) {
        this.zzco = listenerHolder;
        this.zzio = zzbw;
        this.zzip = subscribeOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zza(zzah(), this.zzco, this.zzio, this.zzip, null);
    }
}
