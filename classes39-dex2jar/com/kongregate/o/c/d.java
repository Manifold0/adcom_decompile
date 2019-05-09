// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CountDownLatch;
import android.os.Handler;
import android.os.Looper;
import com.kongregate.android.internal.util.n;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import com.kongregate.android.internal.util.j;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class d<T>
{
    private static final String a;
    private static final ThreadFactory b;
    private static final ScheduledExecutorService c;
    
    static {
        a = "KongregateAPI-WorkQueue-" + new Random().nextInt() + "-";
        b = new b(d.a);
        c = new e(1, d.b, new ThreadPoolExecutor.DiscardPolicy(), g());
    }
    
    public static Future<?> a(final Runnable runnable) {
        if (b()) {
            runnable.run();
            return new a<Object>(null);
        }
        try {
            return d.c.submit(runnable);
        }
        catch (RejectedExecutionException ex) {
            j.c("Execution rejected", ex);
            return null;
        }
    }
    
    public static <T> Future<T> a(final Callable<T> callable) {
        if (b()) {
            return new a<T>(callable);
        }
        try {
            return d.c.submit(callable);
        }
        catch (RejectedExecutionException ex) {
            j.c("Execution rejected", ex);
            return null;
        }
    }
    
    public static ScheduledFuture<?> a(final long n, final long n2, final TimeUnit timeUnit, final Runnable runnable) {
        return d.c.scheduleWithFixedDelay(runnable, n, n2, timeUnit);
    }
    
    public static ScheduledFuture<?> a(final long n, final TimeUnit timeUnit, final Runnable runnable) {
        return d.c.schedule(runnable, n, timeUnit);
    }
    
    public static void a(final Throwable t) {
        try {
            j.d("Exception: attempting to send to crittercism and/or crashlytics", t);
            final Class[] array = { Throwable.class };
            final Object[] array2 = { t };
            n.a("com.crashlytics.android.Crashlytics", "logException", array, array2);
            n.a("com.crittercism.app.Crittercism", "logHandledException", array, array2);
        }
        catch (Exception ex) {
            j.c("logException threw exeption when logging to crittercism or crashlytics: " + t, ex);
        }
    }
    
    public static boolean a() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }
    
    public static void b(final Runnable runnable) {
        if (a()) {
            runnable.run();
            return;
        }
        new Handler(Looper.getMainLooper()).post(runnable);
    }
    
    public static boolean b() {
        return Thread.currentThread().getName().indexOf(d.a) == 0;
    }
    
    public static void c() {
        if (!b()) {
            throw new IllegalStateException("method should only be run on work thread");
        }
    }
    
    public static void c(final Runnable runnable) {
        try {
            runnable.run();
        }
        catch (Exception ex) {
            j.c("safeRun caught exception: ", ex);
            a(ex);
        }
    }
    
    public static void d() {
        if (a()) {
            throw new IllegalStateException("method should  not be run on UI thread");
        }
    }
    
    public static void e() {
        if (a()) {
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        b(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        }
        catch (InterruptedException ex) {}
    }
    
    public static void f() {
        if (b()) {
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        a(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        }
        catch (InterruptedException ex) {}
    }
    
    public static e.a g() {
        return new e.a() {
            @Override
            public void a(final Throwable t) {
                d.a(t);
            }
        };
    }
    
    private static class a<T> implements Future<T>
    {
        private final Callable<T> a;
        
        public a(final Callable<T> a) {
            this.a = a;
        }
        
        @Override
        public boolean cancel(final boolean b) {
            return false;
        }
        
        @Override
        public T get() throws InterruptedException, ExecutionException {
            if (this.a != null) {
                try {
                    return this.a.call();
                }
                catch (Exception ex) {
                    j.c("Execution exception", ex);
                    throw new ExecutionException(ex);
                }
            }
            return null;
        }
        
        @Override
        public T get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.get();
        }
        
        @Override
        public boolean isCancelled() {
            return false;
        }
        
        @Override
        public boolean isDone() {
            return false;
        }
    }
}
