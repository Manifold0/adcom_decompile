// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.RejectedExecutionException;
import javax.annotation.Nullable;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.AbstractExecutorService;

@zzadh
public abstract class zzani extends AbstractExecutorService implements zzaod
{
    @Override
    protected final <T> RunnableFuture<T> newTaskFor(final Runnable runnable, final T t) {
        return new zzaoc<T>(runnable, t);
    }
    
    @Override
    protected final <T> RunnableFuture<T> newTaskFor(final Callable<T> callable) {
        return new zzaoc<T>(callable);
    }
    
    @Override
    public final <T> zzanz<T> zza(final Callable<T> callable) throws RejectedExecutionException {
        return (zzanz<T>)(zzanz)super.submit(callable);
    }
    
    @Override
    public final zzanz<?> zze(final Runnable runnable) throws RejectedExecutionException {
        return (zzanz<?>)(zzanz)super.submit(runnable);
    }
}
