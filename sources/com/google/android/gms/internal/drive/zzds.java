package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

final class zzds extends zzav {
    private final /* synthetic */ List zzfz;
    private final /* synthetic */ zzdp zzgo;

    zzds(zzdp zzdp, GoogleApiClient googleApiClient, List list) {
        this.zzgo = zzdp;
        this.zzfz = list;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgq(this.zzgo.zzk, this.zzfz), new zzgs(this));
    }
}
