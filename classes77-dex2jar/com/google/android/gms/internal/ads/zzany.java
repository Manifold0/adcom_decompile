// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzadh
final class zzany<T> implements zzanz<T>
{
    private final T value;
    private final zzaoa zzcvt;
    
    zzany(final T value) {
        this.value = value;
        (this.zzcvt = new zzaoa()).zzsm();
    }
    
    @Override
    public final boolean cancel(final boolean b) {
        return false;
    }
    
    @Override
    public final T get() {
        return this.value;
    }
    
    @Override
    public final T get(final long n, final TimeUnit timeUnit) {
        return this.value;
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
