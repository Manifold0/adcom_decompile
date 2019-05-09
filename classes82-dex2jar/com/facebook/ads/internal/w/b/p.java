// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.support.annotation.VisibleForTesting;
import java.util.concurrent.BlockingQueue;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executor;

public class p implements Executor
{
    public static final Executor a;
    public static final Executor b;
    private ThreadPoolExecutor c;
    private Executor d;
    private final int e;
    
    static {
        a = new p("ASYNC_TASK", 32);
        b = new p("DB", 0);
    }
    
    @VisibleForTesting
    p(final String s, int availableProcessors) {
        this.d = AsyncTask.THREAD_POOL_EXECUTOR;
        this.e = availableProcessors;
        availableProcessors = Runtime.getRuntime().availableProcessors();
        (this.c = new ThreadPoolExecutor(Math.max(2, Math.min(availableProcessors - 1, 4)), availableProcessors * 2 + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            private final AtomicInteger c = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, String.format(Locale.US, "FAN:%s #%d", s, this.c.getAndIncrement()));
            }
        })).allowCoreThreadTimeOut(true);
    }
    
    @Override
    public void execute(final Runnable runnable) {
        if (this.d instanceof ThreadPoolExecutor && ((ThreadPoolExecutor)this.d).getQueue().size() < this.e) {
            this.d.execute(runnable);
            return;
        }
        this.c.execute(runnable);
    }
}
