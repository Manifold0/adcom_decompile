// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.Executor;

final class zzi<TResult> implements zzq<TResult>
{
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnCompleteListener<TResult> zzl;
    
    public zzi(@NonNull final Executor zzd, @NonNull final OnCompleteListener<TResult> zzl) {
        this.mLock = new Object();
        this.zzd = zzd;
        this.zzl = zzl;
    }
    
    @Override
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzl = null;
        }
    }
    
    @Override
    public final void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.mLock) {
            if (this.zzl == null) {
                return;
            }
            // monitorexit(this.mLock)
            this.zzd.execute(new zzj(this, task));
        }
    }
}
