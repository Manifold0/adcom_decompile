// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;

@zzadh
final class zzanx<T> implements zzanz<T>
{
    private final Throwable cause;
    private final zzaoa zzcvt;
    
    zzanx(final Throwable cause) {
        this.cause = cause;
        (this.zzcvt = new zzaoa()).zzsm();
    }
    
    @Override
    public final boolean cancel(final boolean b) {
        return false;
    }
    
    @Override
    public final T get() throws ExecutionException {
        throw new ExecutionException(this.cause);
    }
    
    @Override
    public final T get(final long n, final TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.cause);
    }
    
    @Override
    public final boolean isCancelled() {
        return false;
    }
    
    @Override
    public final boolean isDone() {
        return true;
    }
    
    @Override
    public final void zza(final Runnable runnable, final Executor executor) {
        this.zzcvt.zza(runnable, executor);
    }
}
