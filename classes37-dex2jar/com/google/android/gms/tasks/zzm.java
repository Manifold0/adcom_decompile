// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.Executor;

final class zzm<TResult> implements zzq<TResult>
{
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnSuccessListener<? super TResult> zzp;
    
    public zzm(@NonNull final Executor zzd, @NonNull final OnSuccessListener<? super TResult> zzp) {
        this.mLock = new Object();
        this.zzd = zzd;
        this.zzp = zzp;
    }
    
    @Override
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzp = null;
        }
    }
    
    @Override
    public final void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.zzp == null) {
                    return;
                }
                // monitorexit(this.mLock)
                this.zzd.execute(new zzn(this, task));
            }
        }
    }
}
