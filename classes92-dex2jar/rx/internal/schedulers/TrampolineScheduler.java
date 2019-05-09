// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import java.util.concurrent.PriorityBlockingQueue;
import rx.subscriptions.BooleanSubscription;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscription;
import rx.Scheduler;

public final class TrampolineScheduler extends Scheduler
{
    public static final TrampolineScheduler INSTANCE;
    
    static {
        INSTANCE = new TrampolineScheduler();
    }
    
    private TrampolineScheduler() {
    }
    
    static int compare(final int n, final int n2) {
        if (n < n2) {
            return -1;
        }
        if (n == n2) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public Worker createWorker() {
        return new InnerCurrentThreadScheduler();
    }
    
    static final class InnerCurrentThreadScheduler extends Worker implements Subscription
    {
        final AtomicInteger counter;
        private final BooleanSubscription innerSubscription;
        final PriorityBlockingQueue<TimedAction> queue;
        private final AtomicInteger wip;
        
        InnerCurrentThreadScheduler() {
            this.counter = new AtomicInteger();
            this.queue = new PriorityBlockingQueue<TimedAction>();
            this.innerSubscription = new BooleanSubscription();
            this.wip = new AtomicInteger();
        }
        
        private Subscription enqueue(final Action0 action0, final long n) {
            if (this.innerSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final TimedAction timedAction = new TimedAction(action0, n, this.counter.incrementAndGet());
            this.queue.add(timedAction);
            if (this.wip.getAndIncrement() == 0) {
                do {
                    final TimedAction timedAction2 = this.queue.poll();
                    if (timedAction2 != null) {
                        timedAction2.action.call();
                    }
                } while (this.wip.decrementAndGet() > 0);
                return Subscriptions.unsubscribed();
            }
            return Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    InnerCurrentThreadScheduler.this.queue.remove(timedAction);
                }
            });
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            return this.enqueue(action0, ((Worker)this).now());
        }
        
        @Override
        public Subscription schedule(final Action0 action0, long n, final TimeUnit timeUnit) {
            n = ((Worker)this).now() + timeUnit.toMillis(n);
            return this.enqueue(new SleepingAction(action0, this, n), n);
        }
        
        @Override
        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }
    }
    
    static final class TimedAction implements Comparable<TimedAction>
    {
        final Action0 action;
        final int count;
        final Long execTime;
        
        TimedAction(final Action0 action, final Long execTime, final int count) {
            this.action = action;
            this.execTime = execTime;
            this.count = count;
        }
        
        @Override
        public int compareTo(final TimedAction timedAction) {
            int n;
            if ((n = this.execTime.compareTo(timedAction.execTime)) == 0) {
                n = TrampolineScheduler.compare(this.count, timedAction.count);
            }
            return n;
        }
    }
}
