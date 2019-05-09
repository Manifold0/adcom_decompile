package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhh extends zzhb<MetadataBuffer> {
    public zzhh(TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfn zzfn) throws RemoteException {
        zzap().setResult(new MetadataBuffer(zzfn.zzal()));
    }
}
