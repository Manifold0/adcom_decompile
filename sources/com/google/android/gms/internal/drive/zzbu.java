package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbu extends zzca {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzbs zzff;

    zzbu(zzbs zzbs, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        this.zzff = zzbs;
        this.zzfb = metadataChangeSet;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzaw.getContext());
        ((zzeo) zzaw.getService()).zza(new zzy(this.zzff.getDriveId(), this.zzfb.zzp()), new zzbw(this));
    }
}
