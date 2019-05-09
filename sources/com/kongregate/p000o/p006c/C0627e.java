package com.kongregate.p000o.p006c;

import com.kongregate.android.internal.util.C0562j;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* renamed from: com.kongregate.o.c.e */
public class C0627e extends ScheduledThreadPoolExecutor {
    /* renamed from: a */
    private C0623a f963a = null;

    /* renamed from: com.kongregate.o.c.e$a */
    public interface C0623a {
        /* renamed from: a */
        void mo1250a(Throwable th);
    }

    public C0627e(int i) {
        super(i);
    }

    public C0627e(int i, ThreadFactory threadFactory) {
        super(i, threadFactory);
    }

    public C0627e(int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, rejectedExecutionHandler);
    }

    public C0627e(int i, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, threadFactory, rejectedExecutionHandler);
    }

    public C0627e(int i, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler, C0623a c0623a) {
        super(i, threadFactory, rejectedExecutionHandler);
        this.f963a = c0623a;
    }

    /* renamed from: a */
    public static ScheduledExecutorService m976a() {
        return new C0627e(1);
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            try {
                Future future = (Future) runnable;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException e) {
            } catch (ExecutionException e2) {
                th = e2.getCause();
            } catch (InterruptedException e3) {
                Thread.currentThread().interrupt();
            }
        }
        if (th == null) {
            return;
        }
        if (this.f963a != null) {
            this.f963a.mo1250a(th);
        } else {
            C0562j.m760c("Exception in ScheduledThreadPoolExecutor", th);
        }
    }
}
