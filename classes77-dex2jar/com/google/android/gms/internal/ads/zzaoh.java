// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;

final class zzaoh extends zzani
{
    private final Executor zzcwd;
    
    private zzaoh(final Executor zzcwd) {
        this.zzcwd = zzcwd;
    }
    
    @Override
    public final boolean awaitTermination(final long n, final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final void execute(final Runnable runnable) {
        this.zzcwd.execute(runnable);
    }
    
    @Override
    public final boolean isShutdown() {
        return false;
    }
    
    @Override
    public final boolean isTerminated() {
        return false;
    }
    
    @Override
    public final void shutdown() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }
}
