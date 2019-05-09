// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import rx.subscriptions.Subscriptions;
import rx.subscriptions.CompositeSubscription;
import rx.internal.util.SubscriptionList;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;

public final class EventLoopsScheduler extends Scheduler implements SchedulerLifecycle
{
    static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
    static final int MAX_THREADS;
    static final FixedSchedulerPool NONE;
    static final PoolWorker SHUTDOWN_WORKER;
    final AtomicReference<FixedSchedulerPool> pool;
    final ThreadFactory threadFactory;
    
    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0);
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        MAX_THREADS = intValue;
        (SHUTDOWN_WORKER = new PoolWorker(RxThreadFactory.NONE)).unsubscribe();
        NONE = new FixedSchedulerPool(null, 0);
    }
    
    public EventLoopsScheduler(final ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        this.pool = new AtomicReference<FixedSchedulerPool>(EventLoopsScheduler.NONE);
        this.start();
    }
    
    @Override
    public Worker createWorker() {
        return new EventLoopWorker(this.pool.get().getEventLoop());
    }
    
    public Subscription scheduleDirect(final Action0 action0) {
        return this.pool.get().getEventLoop().scheduleActual(action0, -1L, TimeUnit.NANOSECONDS);
    }
    
    @Override
    public void shutdown() {
        FixedSchedulerPool fixedSchedulerPool;
        do {
            fixedSchedulerPool = this.pool.get();
            if (fixedSchedulerPool == EventLoopsScheduler.NONE) {
                return;
            }
        } while (!this.pool.compareAndSet(fixedSchedulerPool, EventLoopsScheduler.NONE));
        fixedSchedulerPool.shutdown();
    }
    
    @Override
    public void start() {
        final FixedSchedulerPool fixedSchedulerPool = new FixedSchedulerPool(this.threadFactory, EventLoopsScheduler.MAX_THREADS);
        if (!this.pool.compareAndSet(EventLoopsScheduler.NONE, fixedSchedulerPool)) {
            fixedSchedulerPool.shutdown();
        }
    }
    
    static final class EventLoopWorker extends Worker
    {
        private final SubscriptionList both;
        private final PoolWorker poolWorker;
        private final SubscriptionList serial;
        private final CompositeSubscription timed;
        
        EventLoopWorker(final PoolWorker poolWorker) {
            this.serial = new SubscriptionList();
            this.timed = new CompositeSubscription();
            this.both = new SubscriptionList(new Subscription[] { this.serial, this.timed });
            this.poolWorker = poolWorker;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.both.isUnsubscribed();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            if (this.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() {
                @Override
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, 0L, null, this.serial);
        }
        
        @Override
        public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
            if (this.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() {
                @Override
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, n, timeUnit, this.timed);
        }
        
        @Override
        public void unsubscribe() {
            this.both.unsubscribe();
        }
    }
    
    static final class FixedSchedulerPool
    {
        final int cores;
        final PoolWorker[] eventLoops;
        long n;
        
        FixedSchedulerPool(final ThreadFactory threadFactory, final int cores) {
            this.cores = cores;
            this.eventLoops = new PoolWorker[cores];
            for (int i = 0; i < cores; ++i) {
                this.eventLoops[i] = new PoolWorker(threadFactory);
            }
        }
        
        public PoolWorker getEventLoop() {
            final int cores = this.cores;
            if (cores == 0) {
                return EventLoopsScheduler.SHUTDOWN_WORKER;
            }
            final PoolWorker[] eventLoops = this.eventLoops;
            final long n = this.n;
            this.n = 1L + n;
            return eventLoops[(int)(n % cores)];
        }
        
        public void shutdown() {
            final PoolWorker[] eventLoops = this.eventLoops;
            for (int length = eventLoops.length, i = 0; i < length; ++i) {
                eventLoops[i].unsubscribe();
            }
        }
    }
    
    static final class PoolWorker extends NewThreadWorker
    {
        PoolWorker(final ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
