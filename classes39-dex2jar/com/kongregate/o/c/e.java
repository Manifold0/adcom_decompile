// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class e extends ScheduledThreadPoolExecutor
{
    private a a;
    
    public e(final int n) {
        super(n);
        this.a = null;
    }
    
    public e(final int n, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, rejectedExecutionHandler);
        this.a = null;
    }
    
    public e(final int n, final ThreadFactory threadFactory) {
        super(n, threadFactory);
        this.a = null;
    }
    
    public e(final int n, final ThreadFactory threadFactory, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, threadFactory, rejectedExecutionHandler);
        this.a = null;
    }
    
    public e(final int n, final ThreadFactory threadFactory, final RejectedExecutionHandler rejectedExecutionHandler, final a a) {
        super(n, threadFactory, rejectedExecutionHandler);
        this.a = null;
        this.a = a;
    }
    
    public static ScheduledExecutorService a() {
        return new e(1);
    }
    
    @Override
    protected void afterExecute(final Runnable runnable, final Throwable t) {
        super.afterExecute(runnable, t);
        Throwable cause = t;
        while (true) {
            if (t != null) {
                break Label_0046;
            }
            cause = t;
            if (!(runnable instanceof Future)) {
                break Label_0046;
            }
            try {
                final Future future = (Future)runnable;
                cause = t;
                if (future.isDone()) {
                    future.get();
                    cause = t;
                }
                if (cause != null) {
                    if (this.a == null) {
                        goto Label_0089;
                    }
                    this.a.a(cause);
                }
            }
            catch (ExecutionException ex) {
                cause = ex.getCause();
                continue;
            }
            catch (InterruptedException ex2) {
                Thread.currentThread().interrupt();
                cause = t;
                continue;
            }
            catch (CancellationException ex3) {
                cause = t;
                continue;
            }
            break;
        }
    }
    
    public interface a
    {
        void a(final Throwable p0);
    }
}
