// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
    private final zzu<TResult> zza;
    
    public TaskCompletionSource() {
        this.zza = new zzu<TResult>();
    }
    
    public TaskCompletionSource(@NonNull final CancellationToken cancellationToken) {
        this.zza = new zzu<TResult>();
        cancellationToken.onCanceledRequested(new zzs(this));
    }
    
    @NonNull
    public Task<TResult> getTask() {
        return this.zza;
    }
    
    public void setException(@NonNull final Exception exception) {
        this.zza.setException(exception);
    }
    
    public void setResult(final TResult result) {
        this.zza.setResult(result);
    }
    
    public boolean trySetException(@NonNull final Exception ex) {
        return this.zza.trySetException(ex);
    }
    
    public boolean trySetResult(final TResult tResult) {
        return this.zza.trySetResult(tResult);
    }
}
