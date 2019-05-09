// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzaki
{
    public static final zzaod zzcrj;
    private static final zzaod zzcrk;
    
    static {
        zzcrj = zzaoe.zza(new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), zzct("Default")));
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), zzct("Loader"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        zzcrk = zzaoe.zza(threadPoolExecutor);
    }
    
    public static <T> zzanz<T> zza(final Callable<T> callable) {
        return zzaki.zzcrj.zza(callable);
    }
    
    public static zzanz<?> zzb(final Runnable runnable) {
        return zzaki.zzcrj.zze(runnable);
    }
    
    public static zzanz<?> zzc(final Runnable runnable) {
        return zzaki.zzcrk.zze(runnable);
    }
    
    private static ThreadFactory zzct(final String s) {
        return new zzakj(s);
    }
}
