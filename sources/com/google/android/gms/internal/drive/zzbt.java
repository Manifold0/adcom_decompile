package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbt extends zzby {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ int zzfc;
    private final /* synthetic */ int zzfd;
    private final /* synthetic */ ExecutionOptions zzfe;
    private final /* synthetic */ zzbs zzff;

    zzbt(zzbs zzbs, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, int i, int i2, ExecutionOptions executionOptions) {
        this.zzff = zzbs;
        this.zzfb = metadataChangeSet;
        this.zzfc = i;
        this.zzfd = i2;
        this.zzfe = executionOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzaw.getContext());
        ((zzeo) zzaw.getService()).zza(new zzw(this.zzff.getDriveId(), this.zzfb.zzp(), this.zzfc, this.zzfd, this.zzfe), new zzbv(this));
    }
}
