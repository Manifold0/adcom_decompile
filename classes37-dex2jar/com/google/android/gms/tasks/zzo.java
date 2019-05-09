// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzo<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult>
{
    private final Executor zzd;
    private final zzu<TContinuationResult> zzf;
    private final SuccessContinuation<TResult, TContinuationResult> zzr;
    
    public zzo(@NonNull final Executor zzd, @NonNull final SuccessContinuation<TResult, TContinuationResult> zzr, @NonNull final zzu<TContinuationResult> zzf) {
        this.zzd = zzd;
        this.zzr = zzr;
        this.zzf = zzf;
    }
    
    @Override
    public final void cancel() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final void onCanceled() {
        this.zzf.zza();
    }
    
    @Override
    public final void onComplete(@NonNull final Task<TResult> task) {
        this.zzd.execute(new zzp(this, task));
    }
    
    @Override
    public final void onFailure(@NonNull final Exception exception) {
        this.zzf.setException(exception);
    }
    
    @Override
    public final void onSuccess(final TContinuationResult result) {
        this.zzf.setResult(result);
    }
}
