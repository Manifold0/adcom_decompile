// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.Executor;

final class zzaof implements Executor
{
    private final Handler zzcwc;
    
    zzaof() {
        this.zzcwc = new zzakc(Looper.getMainLooper());
    }
    
    @Override
    public final void execute(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
                return;
            }
            catch (Throwable t) {
                zzbv.zzek();
                zzakk.zza(zzbv.zzeo().getApplicationContext(), t);
                throw t;
            }
        }
        this.zzcwc.post(runnable);
    }
}
