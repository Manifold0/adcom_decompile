// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class c extends f
{
    private volatile boolean a;
    private final ReentrantLock b;
    private final Condition c;
    
    public c(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue) {
        super(n, n2, n3, timeUnit, blockingQueue);
        this.b = new ReentrantLock();
        this.c = this.b.newCondition();
    }
    
    public c(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, n2, n3, timeUnit, blockingQueue, rejectedExecutionHandler);
        this.b = new ReentrantLock();
        this.c = this.b.newCondition();
    }
    
    public c(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final ThreadFactory threadFactory) {
        super(n, n2, n3, timeUnit, blockingQueue, threadFactory);
        this.b = new ReentrantLock();
        this.c = this.b.newCondition();
    }
    
    public c(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue<Runnable> blockingQueue, final ThreadFactory threadFactory, final RejectedExecutionHandler rejectedExecutionHandler) {
        super(n, n2, n3, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        this.b = new ReentrantLock();
        this.c = this.b.newCondition();
    }
    
    public static c a() {
        return new c(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new DiscardPolicy());
    }
    
    public void b() {
        this.b.lock();
        try {
            this.a = true;
        }
        finally {
            this.b.unlock();
        }
    }
    
    @Override
    protected void beforeExecute(final Thread thread, final Runnable runnable) {
        super.beforeExecute(thread, runnable);
        this.b.lock();
        try {
            while (this.a) {
                this.c.await();
            }
        }
        catch (InterruptedException ex) {
            thread.interrupt();
        }
        finally {
            this.b.unlock();
        }
    }
    
    public void c() {
        this.b.lock();
        try {
            this.a = false;
            this.c.signalAll();
        }
        finally {
            this.b.unlock();
        }
    }
}
