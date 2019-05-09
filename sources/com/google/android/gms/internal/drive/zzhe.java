package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhe extends zzhb<DriveFolder> {
    public zzhe(TaskCompletionSource<DriveFolder> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        zzap().setResult(zzfh.getDriveId().asDriveFolder());
    }
}
