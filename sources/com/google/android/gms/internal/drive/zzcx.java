package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcx extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ DriveContents zzfv;

    zzcx(zzch zzch, DriveContents driveContents) {
        this.zzfv = driveContents;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzfv.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzfv.zzh().getRequestId()), new zzhc(taskCompletionSource));
    }
}
