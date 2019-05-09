package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zzhb<T> extends zzl {
    private TaskCompletionSource<T> zzif;

    zzhb(TaskCompletionSource<T> taskCompletionSource) {
        this.zzif = taskCompletionSource;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzif.setException(new ApiException(status));
    }

    public final TaskCompletionSource<T> zzap() {
        return this.zzif;
    }
}
