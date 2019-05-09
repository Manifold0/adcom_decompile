package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.nearby.connection.Payload;

final class zzcc extends zzcy {
    private final /* synthetic */ Payload zzbx;
    private final /* synthetic */ String zzcv;

    zzcc(zzca zzca, GoogleApiClient googleApiClient, String str, Payload payload) {
        this.zzcv = str;
        this.zzbx = payload;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, new String[]{this.zzcv}, this.zzbx, false);
    }
}
