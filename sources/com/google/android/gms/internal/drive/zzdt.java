package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzdt extends zzea {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzdp zzgo;

    zzdt(zzdp zzdp, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        this.zzgo = zzdp;
        this.zzfb = metadataChangeSet;
        super(zzdp, googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzaw.getContext());
        ((zzeo) zzaw.getService()).zza(new zzgz(this.zzgo.zzk, this.zzfb.zzp()), new zzdy(this));
    }
}
