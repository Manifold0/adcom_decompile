package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzn implements StatusListener {
    private final TaskCompletionSource zzip;
    private final ResultConverter zziq;
    private final PendingResult zzix;
    private final zzr zziy;

    zzn(zzr zzr, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter) {
        this.zziy = zzr;
        this.zzix = pendingResult;
        this.zzip = taskCompletionSource;
        this.zziq = resultConverter;
    }

    public final void onComplete(Status status) {
        zzi.zza(this.zziy, this.zzix, this.zzip, this.zziq, status);
    }
}
