// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements zzq<TResult>
{
    private final Executor zzd;
    private final Continuation<TResult, TContinuationResult> zze;
    private final zzu<TContinuationResult> zzf;
    
    public zzc(@NonNull final Executor zzd, @NonNull final Continuation<TResult, TContinuationResult> zze, @NonNull final zzu<TContinuationResult> zzf) {
        this.zzd = zzd;
        this.zze = zze;
        this.zzf = zzf;
    }
    
    @Override
    public final void cancel() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final void onComplete(@NonNull final Task<TResult> task) {
        this.zzd.execute(new zzd(this, task));
    }
}
