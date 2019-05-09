package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzm implements StatusListener {
    private final TaskCompletionSource zzip;
    private final ResultConverter zziw;
    private final PendingResult zzix;

    zzm(ResultConverter resultConverter, PendingResult pendingResult, TaskCompletionSource taskCompletionSource) {
        this.zziw = resultConverter;
        this.zzix = pendingResult;
        this.zzip = taskCompletionSource;
    }

    public final void onComplete(Status status) {
        zzi.zza(this.zziw, this.zzix, this.zzip, status);
    }
}
