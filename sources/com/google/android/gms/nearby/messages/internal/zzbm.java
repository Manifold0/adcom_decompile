package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Message;

final class zzbm extends zzbv {
    private final /* synthetic */ Message zzil;

    zzbm(zzbi zzbi, GoogleApiClient googleApiClient, Message message) {
        this.zzil = message;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zza(zzah(), zzaf.zza(this.zzil));
    }
}
