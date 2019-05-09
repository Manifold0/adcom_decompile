package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzcs extends zzcy {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ ListenerHolder zzdf;

    zzcs(zzca zzca, GoogleApiClient googleApiClient, String str, String str2, ListenerHolder listenerHolder) {
        this.val$name = str;
        this.zzcv = str2;
        this.zzdf = listenerHolder;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, this.val$name, this.zzcv, this.zzdf);
    }
}
