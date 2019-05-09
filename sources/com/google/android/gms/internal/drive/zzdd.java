package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdd extends TaskApiCall<zzaw, Metadata> {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveResource zzfo;

    zzdd(zzch zzch, MetadataChangeSet metadataChangeSet, DriveResource driveResource) {
        this.zzfb = metadataChangeSet;
        this.zzfo = driveResource;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzaw.getContext());
        ((zzeo) zzaw.getService()).zza(new zzgz(this.zzfo.getDriveId(), this.zzfb.zzp()), new zzhj(taskCompletionSource));
    }
}
