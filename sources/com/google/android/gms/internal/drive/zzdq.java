package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdq extends zzea {
    private final /* synthetic */ boolean zzfy = false;
    private final /* synthetic */ zzdp zzgo;

    zzdq(zzdp zzdp, GoogleApiClient googleApiClient, boolean z) {
        this.zzgo = zzdp;
        super(zzdp, googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(this.zzgo.zzk, this.zzfy), new zzdy(this));
    }
}
