package com.chartboost.sdk.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ac {

    /* renamed from: com.chartboost.sdk.impl.ac$1 */
    static class C14171 implements ThreadFactory {
        /* renamed from: a */
        private final AtomicInteger f2980a = new AtomicInteger(1);

        C14171() {
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, "Chartboost Thread #" + this.f2980a.getAndIncrement());
        }
    }

    /* renamed from: a */
    public static ScheduledExecutorService m3363a() {
        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new C14171());
        scheduledThreadPoolExecutor.prestartAllCoreThreads();
        return scheduledThreadPoolExecutor;
    }

    /* renamed from: a */
    public static ExecutorService m3362a(int i) {
        int i2 = i;
        int i3 = i;
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(i2, i3, 10, TimeUnit.SECONDS, new PriorityBlockingQueue());
        threadPoolExecutor.prestartAllCoreThreads();
        return threadPoolExecutor;
    }
}
