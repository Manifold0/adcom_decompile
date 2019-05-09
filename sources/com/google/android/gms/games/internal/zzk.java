package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzk implements StatusListener {
    private final PendingResult zzin;
    private final TaskCompletionSource zzit;
    private final ResultConverter zziu;

    zzk(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter) {
        this.zzin = pendingResult;
        this.zzit = taskCompletionSource;
        this.zziu = resultConverter;
    }

    public final void onComplete(Status status) {
        zzi.zza(this.zzin, this.zzit, this.zziu, status);
    }
}
