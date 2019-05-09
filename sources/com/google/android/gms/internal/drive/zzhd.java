package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhd extends zzhb<DriveFile> {
    public zzhd(TaskCompletionSource<DriveFile> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        zzap().setResult(zzfh.getDriveId().asDriveFile());
    }
}
