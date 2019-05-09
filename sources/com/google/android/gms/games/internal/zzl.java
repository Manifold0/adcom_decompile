package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzl implements StatusListener {
    private final PendingResult zzin;
    private final TaskCompletionSource zzit;
    private final ResultConverter zziu;
    private final zzq zziv;

    zzl(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, zzq zzq) {
        this.zzin = pendingResult;
        this.zzit = taskCompletionSource;
        this.zziu = resultConverter;
        this.zziv = zzq;
    }

    public final void onComplete(Status status) {
        zzi.zza(this.zzin, this.zzit, this.zziu, this.zziv, status);
    }
}
