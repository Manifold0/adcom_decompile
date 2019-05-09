package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzda extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveContents zzfv;

    zzda(zzch zzch, DriveContents driveContents) {
        this.zzfv = driveContents;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzo(this.zzfv.zzh().getRequestId(), false), new zzhl(taskCompletionSource));
    }
}
