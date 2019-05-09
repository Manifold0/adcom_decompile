package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhi extends zzhb<MetadataBuffer> {
    public zzhi(TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfp zzfp) throws RemoteException {
        zzap().setResult(new MetadataBuffer(zzfp.zzam()));
    }
}
