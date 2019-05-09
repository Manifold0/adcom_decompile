package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhc extends zzhb<DriveContents> {
    public zzhc(TaskCompletionSource<DriveContents> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfb zzfb) throws RemoteException {
        zzap().setResult(new zzbi(zzfb.zzai()));
    }
}
