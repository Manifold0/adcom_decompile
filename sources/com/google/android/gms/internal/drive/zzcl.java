package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcl extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveResource zzfo;

    zzcl(zzch zzch, DriveResource driveResource) {
        this.zzfo = driveResource;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzab(this.zzfo.getDriveId()), new zzhl(taskCompletionSource));
    }
}
