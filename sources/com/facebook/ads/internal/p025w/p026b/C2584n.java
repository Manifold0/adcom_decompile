package com.facebook.ads.internal.p025w.p026b;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.facebook.ads.internal.w.b.n */
public class C2584n implements ThreadFactory {
    /* renamed from: a */
    protected final AtomicLong f6363a = new AtomicLong();
    /* renamed from: b */
    private int f6364b = Thread.currentThread().getPriority();

    /* renamed from: a */
    protected String m6650a() {
        return String.format(Locale.US, "com.facebook.ads thread-%d %tF %<tT", new Object[]{Long.valueOf(this.f6363a.incrementAndGet()), Long.valueOf(System.currentTimeMillis())});
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(null, runnable, m6650a(), 0);
        thread.setPriority(this.f6364b);
        return thread;
    }
}
