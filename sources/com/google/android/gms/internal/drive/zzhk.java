package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhk extends zzhb<Boolean> {
    public zzhk(TaskCompletionSource<Boolean> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void onSuccess() throws RemoteException {
        zzap().setResult(Boolean.valueOf(true));
    }
}
