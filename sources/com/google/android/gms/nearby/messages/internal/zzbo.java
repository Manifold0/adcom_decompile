package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzbo extends zzbv {
    private final /* synthetic */ zzbw zzio;
    private final /* synthetic */ SubscribeOptions zzip;
    private final /* synthetic */ PendingIntent zziq;

    zzbo(zzbi zzbi, GoogleApiClient googleApiClient, PendingIntent pendingIntent, zzbw zzbw, SubscribeOptions subscribeOptions) {
        this.zziq = pendingIntent;
        this.zzio = zzbw;
        this.zzip = subscribeOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zza(zzah(), this.zziq, this.zzio, this.zzip);
    }
}
