package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhj extends zzhb<Metadata> {
    public zzhj(TaskCompletionSource<Metadata> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfs zzfs) throws RemoteException {
        zzap().setResult(new zzaa(zzfs.zzan()));
    }
}
