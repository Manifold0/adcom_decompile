package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcm extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveResource zzfo;

    zzcm(zzch zzch, DriveResource driveResource) {
        this.zzfo = driveResource;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgv(this.zzfo.getDriveId()), new zzhl(taskCompletionSource));
    }
}
