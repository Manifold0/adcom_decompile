package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdb extends TaskApiCall<zzaw, DriveFolder> {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveFolder zzfx;

    zzdb(zzch zzch, MetadataChangeSet metadataChangeSet, DriveFolder driveFolder) {
        this.zzfb = metadataChangeSet;
        this.zzfx = driveFolder;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzaw.getContext());
        ((zzeo) zzaw.getService()).zza(new zzy(this.zzfx.getDriveId(), this.zzfb.zzp()), new zzhe(taskCompletionSource));
    }
}
