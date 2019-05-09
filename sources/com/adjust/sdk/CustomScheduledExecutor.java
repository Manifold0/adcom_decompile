package com.adjust.sdk;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class CustomScheduledExecutor {
    private ScheduledThreadPoolExecutor executor;
    private String source;
    private final AtomicInteger threadCounter = new AtomicInteger(1);

    private class RunnableWrapper implements Runnable {
        private Runnable runnable;

        public RunnableWrapper(Runnable runnable) {
            this.runnable = runnable;
        }

        public void run() {
            try {
                this.runnable.run();
            } catch (Throwable t) {
                AdjustFactory.getLogger().error("Runnable error %s", t.getMessage());
            }
        }
    }

    public CustomScheduledExecutor(final String source, boolean doKeepAlive) {
        this.source = source;
        this.executor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {

            /* renamed from: com.adjust.sdk.CustomScheduledExecutor$1$1 */
            class C00851 implements UncaughtExceptionHandler {
                C00851() {
                }

                public void uncaughtException(Thread th, Throwable tr) {
                    AdjustFactory.getLogger().error("Thread %s with error %s", th.getName(), tr.getMessage());
                }
            }

            public Thread newThread(Runnable runnable) {
                Thread thread = Executors.defaultThreadFactory().newThread(new RunnableWrapper(runnable));
                thread.setPriority(1);
                thread.setName(Constants.THREAD_PREFIX + thread.getName() + "-" + source);
                thread.setDaemon(true);
                thread.setUncaughtExceptionHandler(new C00851());
                return thread;
            }
        }, new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                AdjustFactory.getLogger().warn("Runnable %s rejected from %s ", runnable.toString(), source);
            }
        });
        if (!doKeepAlive) {
            this.executor.setKeepAliveTime(10, TimeUnit.MILLISECONDS);
            this.executor.allowCoreThreadTimeOut(true);
        }
    }

    public Future<?> submit(Runnable task) {
        return this.executor.submit(task);
    }

    public void shutdownNow() {
        this.executor.shutdownNow();
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return this.executor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return this.executor.schedule(command, delay, unit);
    }
}
