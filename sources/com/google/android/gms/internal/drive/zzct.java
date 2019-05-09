package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzct extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;

    zzct(zzch zzch, DriveFile driveFile, int i) {
        this.zzfq = driveFile;
        this.zzfr = i;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzfq.getDriveId(), this.zzfr, 0), new zzhc(taskCompletionSource));
    }
}
