package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.nearby.connection.Payload;

final class zzcm extends zzcy {
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ byte[] zzde;

    zzcm(zzca zzca, GoogleApiClient googleApiClient, String str, byte[] bArr) {
        this.zzcv = str;
        this.zzde = bArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, new String[]{this.zzcv}, Payload.fromBytes(this.zzde), true);
    }
}
