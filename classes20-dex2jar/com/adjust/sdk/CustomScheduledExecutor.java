// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class CustomScheduledExecutor
{
    private ScheduledThreadPoolExecutor executor;
    private String source;
    private final AtomicInteger threadCounter;
    
    public CustomScheduledExecutor(final String source, final boolean b) {
        this.threadCounter = new AtomicInteger(1);
        this.source = source;
        this.executor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                final Thread thread = Executors.defaultThreadFactory().newThread(new RunnableWrapper(runnable));
                thread.setPriority(1);
                thread.setName("Adjust-" + thread.getName() + "-" + source);
                thread.setDaemon(true);
                thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(final Thread thread, final Throwable t) {
                        AdjustFactory.getLogger().error("Thread %s with error %s", thread.getName(), t.getMessage());
                    }
                });
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(final Runnable runnable, final ThreadPoolExecutor threadPoolExecutor) {
                AdjustFactory.getLogger().warn("Runnable %s rejected from %s ", runnable.toString(), source);
            }
        });
        if (!b) {
            this.executor.setKeepAliveTime(10L, TimeUnit.MILLISECONDS);
            this.executor.allowCoreThreadTimeOut(true);
        }
    }
    
    public ScheduledFuture<?> schedule(final Runnable runnable, final long n, final TimeUnit timeUnit) {
        return this.executor.schedule(runnable, n, timeUnit);
    }
    
    public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable runnable, final long n, final long n2, final TimeUnit timeUnit) {
        return this.executor.scheduleWithFixedDelay(runnable, n, n2, timeUnit);
    }
    
    public void shutdownNow() {
        this.executor.shutdownNow();
    }
    
    public Future<?> submit(final Runnable runnable) {
        return this.executor.submit(runnable);
    }
    
    private class RunnableWrapper implements Runnable
    {
        private Runnable runnable;
        
        public RunnableWrapper(final Runnable runnable) {
            this.runnable = runnable;
        }
        
        @Override
        public void run() {
            try {
                this.runnable.run();
            }
            catch (Throwable t) {
                AdjustFactory.getLogger().error("Runnable error %s", t.getMessage());
            }
        }
    }
}
