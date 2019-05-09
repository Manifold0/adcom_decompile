package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

final class zzco extends zzcw {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ AdvertisingOptions zzcp;
    private final /* synthetic */ ListenerHolder zzdf;

    zzco(zzca zzca, GoogleApiClient googleApiClient, String str, String str2, ListenerHolder listenerHolder, AdvertisingOptions advertisingOptions) {
        this.val$name = str;
        this.zzcn = str2;
        this.zzdf = listenerHolder;
        this.zzcp = advertisingOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza(this, this.val$name, this.zzcn, this.zzdf, this.zzcp);
    }
}
