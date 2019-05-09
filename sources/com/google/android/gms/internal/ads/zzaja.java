package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaja implements ThreadFactory {
    private final AtomicInteger zzcnz = new AtomicInteger(1);

    zzaja(zzaiy zzaiy) {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AdWorker(SCION_TASK_EXECUTOR) #" + this.zzcnz.getAndIncrement());
    }
}
