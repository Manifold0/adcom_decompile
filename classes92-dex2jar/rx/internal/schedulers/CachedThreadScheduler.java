// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import rx.subscriptions.Subscriptions;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action0;
import java.util.Iterator;
import rx.Subscription;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import rx.subscriptions.CompositeSubscription;
import rx.internal.util.RxThreadFactory;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;

public final class CachedThreadScheduler extends Scheduler implements SchedulerLifecycle
{
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final TimeUnit KEEP_ALIVE_UNIT;
    static final CachedWorkerPool NONE;
    static final ThreadWorker SHUTDOWN_THREADWORKER;
    final AtomicReference<CachedWorkerPool> pool;
    final ThreadFactory threadFactory;
    
    static {
        KEEP_ALIVE_UNIT = TimeUnit.SECONDS;
        (SHUTDOWN_THREADWORKER = new ThreadWorker(RxThreadFactory.NONE)).unsubscribe();
        (NONE = new CachedWorkerPool(null, 0L, null)).shutdown();
    }
    
    public CachedThreadScheduler(final ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        this.pool = new AtomicReference<CachedWorkerPool>(CachedThreadScheduler.NONE);
        this.start();
    }
    
    @Override
    public Worker createWorker() {
        return new EventLoopWorker((CachedWorkerPool)this.pool.get());
    }
    
    @Override
    public void shutdown() {
        CachedWorkerPool cachedWorkerPool;
        do {
            cachedWorkerPool = this.pool.get();
            if (cachedWorkerPool == CachedThreadScheduler.NONE) {
                return;
            }
        } while (!this.pool.compareAndSet(cachedWorkerPool, CachedThreadScheduler.NONE));
        cachedWorkerPool.shutdown();
    }
    
    @Override
    public void start() {
        final CachedWorkerPool cachedWorkerPool = new CachedWorkerPool(this.threadFactory, 60L, CachedThreadScheduler.KEEP_ALIVE_UNIT);
        if (!this.pool.compareAndSet(CachedThreadScheduler.NONE, cachedWorkerPool)) {
            cachedWorkerPool.shutdown();
        }
    }
    
    static final class CachedWorkerPool
    {
        private final CompositeSubscription allWorkers;
        private final ScheduledExecutorService evictorService;
        private final Future<?> evictorTask;
        private final ConcurrentLinkedQueue<ThreadWorker> expiringWorkerQueue;
        private final long keepAliveTime;
        private final ThreadFactory threadFactory;
        
        CachedWorkerPool(final ThreadFactory threadFactory, long nanos, final TimeUnit timeUnit) {
            this.threadFactory = threadFactory;
            if (timeUnit != null) {
                nanos = timeUnit.toNanos(nanos);
            }
            else {
                nanos = 0L;
            }
            this.keepAliveTime = nanos;
            this.expiringWorkerQueue = new ConcurrentLinkedQueue<ThreadWorker>();
            this.allWorkers = new CompositeSubscription();
            ScheduledExecutorService scheduledThreadPool = null;
            Future<?> scheduleWithFixedDelay = null;
            if (timeUnit != null) {
                scheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                    @Override
                    public Thread newThread(final Runnable runnable) {
                        final Thread thread = threadFactory.newThread(runnable);
                        thread.setName(thread.getName() + " (Evictor)");
                        return thread;
                    }
                });
                NewThreadWorker.tryEnableCancelPolicy(scheduledThreadPool);
                scheduleWithFixedDelay = scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        CachedWorkerPool.this.evictExpiredWorkers();
                    }
                }, this.keepAliveTime, this.keepAliveTime, TimeUnit.NANOSECONDS);
            }
            this.evictorService = scheduledThreadPool;
            this.evictorTask = scheduleWithFixedDelay;
        }
        
        void evictExpiredWorkers() {
            if (!this.expiringWorkerQueue.isEmpty()) {
                final long now = this.now();
                for (final ThreadWorker threadWorker : this.expiringWorkerQueue) {
                    if (threadWorker.getExpirationTime() > now) {
                        break;
                    }
                    if (!this.expiringWorkerQueue.remove(threadWorker)) {
                        continue;
                    }
                    this.allWorkers.remove(threadWorker);
                }
            }
        }
        
        ThreadWorker get() {
            if (this.allWorkers.isUnsubscribed()) {
                return CachedThreadScheduler.SHUTDOWN_THREADWORKER;
            }
            while (!this.expiringWorkerQueue.isEmpty()) {
                final ThreadWorker threadWorker = this.expiringWorkerQueue.poll();
                if (threadWorker != null) {
                    return threadWorker;
                }
            }
            final ThreadWorker threadWorker2 = new ThreadWorker(this.threadFactory);
            this.allWorkers.add(threadWorker2);
            return threadWorker2;
        }
        
        long now() {
            return System.nanoTime();
        }
        
        void release(final ThreadWorker threadWorker) {
            threadWorker.setExpirationTime(this.now() + this.keepAliveTime);
            this.expiringWorkerQueue.offer(threadWorker);
        }
        
        void shutdown() {
            try {
                if (this.evictorTask != null) {
                    this.evictorTask.cancel(true);
                }
                if (this.evictorService != null) {
                    this.evictorService.shutdownNow();
                }
            }
            finally {
                this.allWorkers.unsubscribe();
            }
        }
    }
    
    static final class EventLoopWorker extends Worker implements Action0
    {
        private final CompositeSubscription innerSubscription;
        final AtomicBoolean once;
        private final CachedWorkerPool pool;
        private final ThreadWorker threadWorker;
        
        EventLoopWorker(final CachedWorkerPool pool) {
            this.innerSubscription = new CompositeSubscription();
            this.pool = pool;
            this.once = new AtomicBoolean();
            this.threadWorker = pool.get();
        }
        
        @Override
        public void call() {
            this.pool.release(this.threadWorker);
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            return this.schedule(action0, 0L, null);
        }
        
        @Override
        public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
            if (this.innerSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final ScheduledAction scheduleActual = this.threadWorker.scheduleActual(new Action0() {
                @Override
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, n, timeUnit);
            this.innerSubscription.add(scheduleActual);
            scheduleActual.addParent(this.innerSubscription);
            return scheduleActual;
        }
        
        @Override
        public void unsubscribe() {
            if (this.once.compareAndSet(false, true)) {
                this.threadWorker.schedule(this);
            }
            this.innerSubscription.unsubscribe();
        }
    }
    
    static final class ThreadWorker extends NewThreadWorker
    {
        private long expirationTime;
        
        ThreadWorker(final ThreadFactory threadFactory) {
            super(threadFactory);
            this.expirationTime = 0L;
        }
        
        public long getExpirationTime() {
            return this.expirationTime;
        }
        
        public void setExpirationTime(final long expirationTime) {
            this.expirationTime = expirationTime;
        }
    }
}
