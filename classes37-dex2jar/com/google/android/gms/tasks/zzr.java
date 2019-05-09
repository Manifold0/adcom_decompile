// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import java.util.Queue;

final class zzr<TResult>
{
    private final Object mLock;
    @GuardedBy("mLock")
    private Queue<zzq<TResult>> zzt;
    @GuardedBy("mLock")
    private boolean zzu;
    
    zzr() {
        this.mLock = new Object();
    }
    
    public final void zza(@NonNull final Task<TResult> task) {
        while (true) {
            // monitorexit(mLock)
            while (true) {
                final zzq<TResult> zzq;
                synchronized (this.mLock) {
                    if (this.zzt == null || this.zzu) {
                        return;
                    }
                    this.zzu = true;
                    // monitorexit(this.mLock)
                    final Object mLock = this.mLock;
                    synchronized (this.mLock) {
                        zzq = this.zzt.poll();
                        if (zzq == null) {
                            this.zzu = false;
                            return;
                        }
                    }
                }
                final Task<TResult> task2;
                zzq.onComplete(task2);
                continue;
            }
        }
    }
    
    public final void zza(@NonNull final zzq<TResult> zzq) {
        synchronized (this.mLock) {
            if (this.zzt == null) {
                this.zzt = new ArrayDeque<zzq<TResult>>();
            }
            this.zzt.add(zzq);
        }
    }
}
