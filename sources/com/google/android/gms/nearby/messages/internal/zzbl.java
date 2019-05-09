package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.PublishOptions;

final class zzbl extends zzbv {
    private final /* synthetic */ Message zzil;
    private final /* synthetic */ zzbt zzim;
    private final /* synthetic */ PublishOptions zzin;

    zzbl(zzbi zzbi, GoogleApiClient googleApiClient, Message message, zzbt zzbt, PublishOptions publishOptions) {
        this.zzil = message;
        this.zzim = zzbt;
        this.zzin = publishOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzah) anyClient).zza(zzah(), zzaf.zza(this.zzil), this.zzim, this.zzin);
    }
}
