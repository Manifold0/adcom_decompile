// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.api.Status;
import android.support.annotation.Nullable;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.common.api.PendingResult$StatusListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;

public final class zzi
{
    private static final zzr zzim;
    
    static {
        zzim = zzo.zziz;
    }
    
    public static <R, PendingR extends Result> Task<R> toTask(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult$StatusListener)new zzk(pendingResult, taskCompletionSource, pendingResultUtil$ResultConverter));
        return (Task<R>)taskCompletionSource.getTask();
    }
    
    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter) {
        return zza((com.google.android.gms.common.api.PendingResult<Result>)pendingResult, (com.google.android.gms.common.internal.PendingResultUtil$ResultConverter<Result, R>)pendingResultUtil$ResultConverter, null);
    }
    
    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter, @Nullable final zzq<PendingR> zzq) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult$StatusListener)new zzl(pendingResult, taskCompletionSource, pendingResultUtil$ResultConverter, zzq));
        return (Task<AnnotatedData<R>>)taskCompletionSource.getTask();
    }
    
    public static <R, PendingR extends Result> Task<R> zza(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final zzr zzr, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult$StatusListener)new zzn(zzr, pendingResult, taskCompletionSource, pendingResultUtil$ResultConverter));
        return (Task<R>)taskCompletionSource.getTask();
    }
    
    public static <R, PendingR extends Result, ExceptionData> Task<R> zza(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final zzr zzr, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter, @NonNull final PendingResultUtil$ResultConverter<PendingR, ExceptionData> pendingResultUtil$ResultConverter2, @NonNull final zzp<ExceptionData> zzp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult$StatusListener)new zzj(pendingResult, zzr, taskCompletionSource, pendingResultUtil$ResultConverter, pendingResultUtil$ResultConverter2, zzp));
        return (Task<R>)taskCompletionSource.getTask();
    }
    
    public static <R extends Releasable, PendingR extends Result> Task<AnnotatedData<R>> zzb(@NonNull final PendingResult<PendingR> pendingResult, @NonNull final PendingResultUtil$ResultConverter<PendingR, R> pendingResultUtil$ResultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult$StatusListener)new zzm(pendingResultUtil$ResultConverter, pendingResult, taskCompletionSource));
        return (Task<AnnotatedData<R>>)taskCompletionSource.getTask();
    }
    
    private static Status zzc(@NonNull final Status status) {
        final int zzb = GamesClientStatusCodes.zzb(status.getStatusCode());
        Status zza = status;
        if (zzb != status.getStatusCode()) {
            if (!GamesStatusCodes.getStatusString(status.getStatusCode()).equals(status.getStatusMessage())) {
                return new Status(zzb, status.getStatusMessage());
            }
            zza = GamesClientStatusCodes.zza(zzb);
        }
        return zza;
    }
}
