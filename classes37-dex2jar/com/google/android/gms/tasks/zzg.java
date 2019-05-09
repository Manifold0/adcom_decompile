// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.Executor;

final class zzg<TResult> implements zzq<TResult>
{
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnCanceledListener zzj;
    
    public zzg(@NonNull final Executor zzd, @NonNull final OnCanceledListener zzj) {
        this.mLock = new Object();
        this.zzd = zzd;
        this.zzj = zzj;
    }
    
    @Override
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzj = null;
        }
    }
    
    @Override
    public final void onComplete(@NonNull final Task task) {
        if (task.isCanceled()) {
            synchronized (this.mLock) {
                if (this.zzj == null) {
                    return;
                }
                // monitorexit(this.mLock)
                this.zzd.execute(new zzh(this));
            }
        }
    }
}
