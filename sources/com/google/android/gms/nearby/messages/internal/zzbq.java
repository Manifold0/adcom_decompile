package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbq extends zzbv {
    private final /* synthetic */ PendingIntent zziq;

    zzbq(zzbi zzbi, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.zziq = pendingIntent;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zza(zzah(), this.zziq);
    }
}
