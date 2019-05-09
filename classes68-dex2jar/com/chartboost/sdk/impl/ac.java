// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public final class ac
{
    public static ExecutorService a(final int n) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(n, n, 10L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        threadPoolExecutor.prestartAllCoreThreads();
        return threadPoolExecutor;
    }
    
    public static ScheduledExecutorService a() {
        final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            private final AtomicInteger a = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "Chartboost Thread #" + this.a.getAndIncrement());
            }
        });
        scheduledThreadPoolExecutor.prestartAllCoreThreads();
        return scheduledThreadPoolExecutor;
    }
}
