package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzax implements ResultHolder<Status> {
    private final /* synthetic */ TaskCompletionSource zzib;

    zzax(zzak zzak, TaskCompletionSource taskCompletionSource) {
        this.zzib = taskCompletionSource;
    }

    public final void setFailedResult(Status status) {
        this.zzib.setException(new ApiException(status));
    }

    public final /* synthetic */ void setResult(Object obj) {
        this.zzib.setResult(null);
    }
}
