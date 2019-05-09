// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import java.util.concurrent.FutureTask;

@zzadh
final class zzaoc<V> extends FutureTask<V> implements zzanz<V>
{
    private final zzaoa zzcvt;
    
    zzaoc(final Runnable runnable, @Nullable final V v) {
        super(runnable, v);
        this.zzcvt = new zzaoa();
    }
    
    zzaoc(final Callable<V> callable) {
        super(callable);
        this.zzcvt = new zzaoa();
    }
    
    @Override
    protected final void done() {
        this.zzcvt.zzsm();
    }
    
    @Override
    public final void zza(final Runnable runnable, final Executor executor) {
        this.zzcvt.zza(runnable, executor);
    }
}
