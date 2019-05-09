// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzaja implements ThreadFactory
{
    private final AtomicInteger zzcnz;
    
    zzaja(final zzaiy zzaiy) {
        this.zzcnz = new AtomicInteger(1);
    }
    
    @Override
    public final Thread newThread(final Runnable runnable) {
        return new Thread(runnable, new StringBuilder(42).append("AdWorker(SCION_TASK_EXECUTOR) #").append(this.zzcnz.getAndIncrement()).toString());
    }
}
