package com.google.android.gms.games.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

public final class zzi {
    private static final zzr zzim = zzo.zziz;

    public static <R, PendingR extends Result> Task<R> toTask(@NonNull PendingResult<PendingR> pendingResult, @NonNull ResultConverter<PendingR, R> resultConverter) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zzk(pendingResult, taskCompletionSource, resultConverter));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(@NonNull PendingResult<PendingR> pendingResult, @NonNull ResultConverter<PendingR, R> resultConverter) {
        return zza((PendingResult) pendingResult, (ResultConverter) resultConverter, null);
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(@NonNull PendingResult<PendingR> pendingResult, @NonNull ResultConverter<PendingR, R> resultConverter, @Nullable zzq<PendingR> zzq) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zzl(pendingResult, taskCompletionSource, resultConverter, zzq));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<R> zza(@NonNull PendingResult<PendingR> pendingResult, @NonNull zzr zzr, @NonNull ResultConverter<PendingR, R> resultConverter) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zzn(zzr, pendingResult, taskCompletionSource, resultConverter));
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result, ExceptionData> Task<R> zza(@NonNull PendingResult<PendingR> pendingResult, @NonNull zzr zzr, @NonNull ResultConverter<PendingR, R> resultConverter, @NonNull ResultConverter<PendingR, ExceptionData> resultConverter2, @NonNull zzp<ExceptionData> zzp) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zzj(pendingResult, zzr, taskCompletionSource, resultConverter, resultConverter2, zzp));
        return taskCompletionSource.getTask();
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, zzr zzr, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, ResultConverter resultConverter2, zzp zzp, Status status) {
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (zzr.zza(status)) {
            taskCompletionSource.setResult(resultConverter.convert(await));
            return;
        }
        Object convert = resultConverter2.convert(await);
        if (convert != null) {
            taskCompletionSource.setException(zzp.zza(zzc(status), convert));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, Status status) {
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (status.isSuccess()) {
            taskCompletionSource.setResult(resultConverter.convert(await));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, zzq zzq, Status status) {
        boolean z = status.getStatusCode() == 3;
        Result await = pendingResult.await(0, TimeUnit.MILLISECONDS);
        if (status.isSuccess() || z) {
            taskCompletionSource.setResult(new AnnotatedData(resultConverter.convert(await), z));
            return;
        }
        if (!(await == null || zzq == null)) {
            zzq.release(await);
        }
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
    }

    static final /* synthetic */ void zza(ResultConverter resultConverter, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, Status status) {
        boolean z = status.getStatusCode() == 3;
        Releasable releasable = (Releasable) resultConverter.convert(pendingResult.await(0, TimeUnit.MILLISECONDS));
        if (status.isSuccess() || z) {
            taskCompletionSource.setResult(new AnnotatedData(releasable, z));
            return;
        }
        if (releasable != null) {
            releasable.release();
        }
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
    }

    static final /* synthetic */ void zza(zzr zzr, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, ResultConverter resultConverter, Status status) {
        if (zzr.zza(status)) {
            taskCompletionSource.setResult(resultConverter.convert(pendingResult.await(0, TimeUnit.MILLISECONDS)));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }

    public static <R extends Releasable, PendingR extends Result> Task<AnnotatedData<R>> zzb(@NonNull PendingResult<PendingR> pendingResult, @NonNull ResultConverter<PendingR, R> resultConverter) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zzm(resultConverter, pendingResult, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private static Status zzc(@NonNull Status status) {
        int zzb = GamesClientStatusCodes.zzb(status.getStatusCode());
        return zzb != status.getStatusCode() ? GamesStatusCodes.getStatusString(status.getStatusCode()).equals(status.getStatusMessage()) ? GamesClientStatusCodes.zza(zzb) : new Status(zzb, status.getStatusMessage()) : status;
    }
}
