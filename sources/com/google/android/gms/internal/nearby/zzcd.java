package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.nearby.connection.Payload;
import java.util.List;

final class zzcd extends zzcy {
    private final /* synthetic */ Payload zzbx;
    private final /* synthetic */ List zzcw;

    zzcd(zzca zzca, GoogleApiClient googleApiClient, List list, Payload payload) {
        this.zzcw = list;
        this.zzbx = payload;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, (String[]) this.zzcw.toArray(new String[0]), this.zzbx, false);
    }
}
