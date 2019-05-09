// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.Executor;

final class zzk<TResult> implements zzq<TResult>
{
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnFailureListener zzn;
    
    public zzk(@NonNull final Executor zzd, @NonNull final OnFailureListener zzn) {
        this.mLock = new Object();
        this.zzd = zzd;
        this.zzn = zzn;
    }
    
    @Override
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzn = null;
        }
    }
    
    @Override
    public final void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful() && !task.isCanceled()) {
            synchronized (this.mLock) {
                if (this.zzn == null) {
                    return;
                }
                // monitorexit(this.mLock)
                this.zzd.execute(new zzl(this, task));
            }
        }
    }
}
