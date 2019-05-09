package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhf extends zzhb<DriveId> {
    public zzhf(TaskCompletionSource<DriveId> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        zzap().setResult(zzfh.getDriveId());
    }

    public final void zza(zzfs zzfs) throws RemoteException {
        zzap().setResult(new zzaa(zzfs.zzan()).getDriveId());
    }
}
