package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzby<T> implements ResultHolder<T> {
    private final TaskCompletionSource<Void> zzcu;

    zzby(zzbd zzbd, TaskCompletionSource<Void> taskCompletionSource) {
        this.zzcu = taskCompletionSource;
    }

    public final void setFailedResult(Status status) {
        this.zzcu.setException(new ApiException(status));
    }

    public final void setResult(T t) {
        this.zzcu.setResult(null);
    }
}
