package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbm extends zzav {
    private final /* synthetic */ zzbi zzet;

    zzbm(zzbi zzbi, GoogleApiClient googleApiClient) {
        this.zzet = zzbi;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzo(this.zzet.zzeq.getRequestId(), false), new zzgs(this));
    }
}
