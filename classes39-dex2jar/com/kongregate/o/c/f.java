// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import com.kongregate.android.internal.util.j;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

public class f extends ThreadPoolExecutor
{
    public f(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue) {
        super(n, n2, n3, timeUnit, blockingQueue);
    }
    
    public f(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, n2, n3, timeUnit, blockingQueue, rejectedExecutionHandler);
    }
    
    public f(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final ThreadFactory threadFactory) {
        super(n, n2, n3, timeUnit, blockingQueue, threadFactory);
    }
    
    public f(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final ThreadFactory threadFactory, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, n2, n3, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }
    
    @Override
    protected void afterExecute(final Runnable runnable, final Throwable t) {
        super.afterExecute(runnable, t);
        if (t != null) {
            j.c("Exception in ThreadPoolExecutor", t);
        }
    }
}
