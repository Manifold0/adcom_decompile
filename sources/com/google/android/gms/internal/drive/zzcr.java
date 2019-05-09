package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcr extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveResource zzfo;

    zzcr(zzch zzch, DriveResource driveResource) {
        this.zzfo = driveResource;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        if (zzaw.zzea) {
            ((zzeo) zzaw.getService()).zza(new zzj(1, this.zzfo.getDriveId()), null, null, new zzhl(taskCompletionSource));
            return;
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }
}
