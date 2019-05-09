package com.facebook.ads.internal.p025w.p026b;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.w.b.p */
public class C2587p implements Executor {
    /* renamed from: a */
    public static final Executor f6372a = new C2587p("ASYNC_TASK", 32);
    /* renamed from: b */
    public static final Executor f6373b = new C2587p("DB", 0);
    /* renamed from: c */
    private ThreadPoolExecutor f6374c;
    /* renamed from: d */
    private Executor f6375d = AsyncTask.THREAD_POOL_EXECUTOR;
    /* renamed from: e */
    private final int f6376e;

    @VisibleForTesting
    C2587p(final String str, int i) {
        this.f6376e = i;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        this.f6374c = new ThreadPoolExecutor(Math.max(2, Math.min(availableProcessors - 1, 4)), (availableProcessors * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory(this) {
            /* renamed from: b */
            final /* synthetic */ C2587p f6370b;
            /* renamed from: c */
            private final AtomicInteger f6371c = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, String.format(Locale.US, "FAN:%s #%d", new Object[]{str, Integer.valueOf(this.f6371c.getAndIncrement())}));
            }
        });
        this.f6374c.allowCoreThreadTimeOut(true);
    }

    public void execute(Runnable runnable) {
        if (!(this.f6375d instanceof ThreadPoolExecutor) || ((ThreadPoolExecutor) this.f6375d).getQueue().size() >= this.f6376e) {
            this.f6374c.execute(runnable);
        } else {
            this.f6375d.execute(runnable);
        }
    }
}
