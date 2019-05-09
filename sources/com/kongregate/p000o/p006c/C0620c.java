package com.kongregate.p000o.p006c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.kongregate.o.c.c */
public class C0620c extends C0619f {
    /* renamed from: a */
    private volatile boolean f954a;
    /* renamed from: b */
    private final ReentrantLock f955b = new ReentrantLock();
    /* renamed from: c */
    private final Condition f956c = this.f955b.newCondition();

    /* renamed from: a */
    public static C0620c m957a() {
        return new C0620c(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DiscardPolicy());
    }

    public C0620c(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i2, j, timeUnit, blockingQueue);
    }

    public C0620c(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, (BlockingQueue) blockingQueue, threadFactory);
    }

    public C0620c(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, (BlockingQueue) blockingQueue, rejectedExecutionHandler);
    }

    public C0620c(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }

    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        this.f955b.lock();
        while (this.f954a) {
            try {
                this.f956c.await();
            } catch (InterruptedException e) {
                thread.interrupt();
            } finally {
                this.f955b.unlock();
            }
        }
    }

    /* renamed from: b */
    public void m958b() {
        this.f955b.lock();
        try {
            this.f954a = true;
        } finally {
            this.f955b.unlock();
        }
    }

    /* renamed from: c */
    public void m959c() {
        this.f955b.lock();
        try {
            this.f954a = false;
            this.f956c.signalAll();
        } finally {
            this.f955b.unlock();
        }
    }
}
