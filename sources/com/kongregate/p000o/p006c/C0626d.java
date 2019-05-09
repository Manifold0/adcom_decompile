package com.kongregate.p000o.p006c;

import android.os.Handler;
import android.os.Looper;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0566n;
import com.kongregate.p000o.p006c.C0627e.C0623a;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.kongregate.o.c.d */
public class C0626d<T> {
    /* renamed from: a */
    private static final String f960a = ("KongregateAPI-WorkQueue-" + new Random().nextInt() + "-");
    /* renamed from: b */
    private static final ThreadFactory f961b = new C0618b(f960a);
    /* renamed from: c */
    private static final ScheduledExecutorService f962c = new C0627e(1, f961b, new DiscardPolicy(), C0626d.m975g());

    /* renamed from: com.kongregate.o.c.d$3 */
    static class C06243 implements C0623a {
        C06243() {
        }

        /* renamed from: a */
        public void mo1250a(Throwable th) {
            C0626d.m966a(th);
        }
    }

    /* renamed from: com.kongregate.o.c.d$a */
    private static class C0625a<T> implements Future<T> {
        /* renamed from: a */
        private final Callable<T> f959a;

        public C0625a(Callable<T> callable) {
            this.f959a = callable;
        }

        public boolean cancel(boolean z) {
            return false;
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return false;
        }

        public T get() throws InterruptedException, ExecutionException {
            if (this.f959a == null) {
                return null;
            }
            try {
                return this.f959a.call();
            } catch (Throwable e) {
                C0562j.m760c("Execution exception", e);
                throw new ExecutionException(e);
            }
        }

        public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return get();
        }
    }

    /* renamed from: a */
    public static boolean m967a() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    /* renamed from: b */
    public static boolean m969b() {
        return Thread.currentThread().getName().indexOf(f960a) == 0;
    }

    /* renamed from: c */
    public static void m970c() {
        if (!C0626d.m969b()) {
            throw new IllegalStateException("method should only be run on work thread");
        }
    }

    /* renamed from: d */
    public static void m972d() {
        if (C0626d.m967a()) {
            throw new IllegalStateException("method should  not be run on UI thread");
        }
    }

    /* renamed from: a */
    public static ScheduledFuture<?> m964a(long j, long j2, TimeUnit timeUnit, Runnable runnable) {
        return f962c.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static ScheduledFuture<?> m965a(long j, TimeUnit timeUnit, Runnable runnable) {
        return f962c.schedule(runnable, j, timeUnit);
    }

    /* renamed from: a */
    public static Future<?> m962a(Runnable runnable) {
        if (C0626d.m969b()) {
            runnable.run();
            return new C0625a(null);
        }
        try {
            return f962c.submit(runnable);
        } catch (Throwable e) {
            C0562j.m760c("Execution rejected", e);
            return null;
        }
    }

    /* renamed from: a */
    public static <T> Future<T> m963a(Callable<T> callable) {
        if (C0626d.m969b()) {
            return new C0625a(callable);
        }
        try {
            return f962c.submit(callable);
        } catch (Throwable e) {
            C0562j.m760c("Execution rejected", e);
            return null;
        }
    }

    /* renamed from: b */
    public static void m968b(Runnable runnable) {
        if (C0626d.m967a()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* renamed from: c */
    public static void m971c(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            C0562j.m760c("safeRun caught exception: ", e);
            C0626d.m966a(e);
        }
    }

    /* renamed from: e */
    public static void m973e() {
        if (!C0626d.m967a()) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            C0626d.m968b(new Runnable() {
                public void run() {
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
            }
        }
    }

    /* renamed from: f */
    public static void m974f() {
        if (!C0626d.m969b()) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            C0626d.m962a(new Runnable() {
                public void run() {
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
            }
        }
    }

    /* renamed from: g */
    public static C0623a m975g() {
        return new C06243();
    }

    /* renamed from: a */
    public static void m966a(Throwable th) {
        try {
            C0562j.m762d("Exception: attempting to send to crittercism and/or crashlytics", th);
            Class[] clsArr = new Class[]{Throwable.class};
            Object[] objArr = new Object[]{th};
            C0566n.m779a("com.crashlytics.android.Crashlytics", "logException", clsArr, objArr);
            C0566n.m779a("com.crittercism.app.Crittercism", "logHandledException", clsArr, objArr);
        } catch (Throwable e) {
            C0562j.m760c("logException threw exeption when logging to crittercism or crashlytics: " + th, e);
        }
    }
}
