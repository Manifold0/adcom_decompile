package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzj implements StatusListener {
    private final PendingResult zzin;
    private final zzr zzio;
    private final TaskCompletionSource zzip;
    private final ResultConverter zziq;
    private final ResultConverter zzir;
    private final zzp zzis;

    zzj(PendingResult pendingResult, zzr zzr, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, ResultConverter resultConverter2, zzp zzp) {
        this.zzin = pendingResult;
        this.zzio = zzr;
        this.zzip = taskCompletionSource;
        this.zziq = resultConverter;
        this.zzir = resultConverter2;
        this.zzis = zzp;
    }

    public final void onComplete(Status status) {
        zzi.zza(this.zzin, this.zzio, this.zzip, this.zziq, this.zzir, this.zzis, status);
    }
}
