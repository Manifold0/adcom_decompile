package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhl extends zzhb<Void> {
    public zzhl(TaskCompletionSource<Void> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void onSuccess() throws RemoteException {
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, zzap());
    }
}
