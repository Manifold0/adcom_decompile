// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

import rx.subscriptions.Subscriptions;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import java.util.concurrent.TimeUnit;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import rx.Scheduler;

public class TestScheduler extends Scheduler
{
    static long counter;
    final Queue<TimedAction> queue;
    long time;
    
    public TestScheduler() {
        this.queue = new PriorityQueue<TimedAction>(11, (Comparator<? super TimedAction>)new CompareActionsByTime());
    }
    
    private void triggerActions(final long time) {
        while (!this.queue.isEmpty()) {
            final TimedAction timedAction = this.queue.peek();
            if (timedAction.time > time) {
                break;
            }
            long time2;
            if (timedAction.time == 0L) {
                time2 = this.time;
            }
            else {
                time2 = timedAction.time;
            }
            this.time = time2;
            this.queue.remove();
            if (timedAction.scheduler.isUnsubscribed()) {
                continue;
            }
            timedAction.action.call();
        }
        this.time = time;
    }
    
    public void advanceTimeBy(final long n, final TimeUnit timeUnit) {
        this.advanceTimeTo(this.time + timeUnit.toNanos(n), TimeUnit.NANOSECONDS);
    }
    
    public void advanceTimeTo(final long n, final TimeUnit timeUnit) {
        this.triggerActions(timeUnit.toNanos(n));
    }
    
    @Override
    public Worker createWorker() {
        return new InnerTestScheduler();
    }
    
    @Override
    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.time);
    }
    
    public void triggerActions() {
        this.triggerActions(this.time);
    }
    
    static final class CompareActionsByTime implements Comparator<TimedAction>
    {
        @Override
        public int compare(final TimedAction timedAction, final TimedAction timedAction2) {
            if (timedAction.time == timedAction2.time) {
                if (timedAction.count >= timedAction2.count) {
                    if (timedAction.count > timedAction2.count) {
                        return 1;
                    }
                    return 0;
                }
            }
            else if (timedAction.time >= timedAction2.time) {
                if (timedAction.time > timedAction2.time) {
                    return 1;
                }
                return 0;
            }
            return -1;
        }
    }
    
    final class InnerTestScheduler extends Worker
    {
        private final BooleanSubscription s;
        
        InnerTestScheduler() {
            this.s = new BooleanSubscription();
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }
        
        @Override
        public long now() {
            return TestScheduler.this.now();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            final TimedAction timedAction = new TimedAction(this, 0L, action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }
        
        @Override
        public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
            final TimedAction timedAction = new TimedAction(this, TestScheduler.this.time + timeUnit.toNanos(n), action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }
        
        @Override
        public void unsubscribe() {
            this.s.unsubscribe();
        }
    }
    
    static final class TimedAction
    {
        final Action0 action;
        private final long count;
        final Worker scheduler;
        final long time;
        
        TimedAction(final Worker scheduler, final long time, final Action0 action) {
            final long counter = TestScheduler.counter;
            TestScheduler.counter = 1L + counter;
            this.count = counter;
            this.time = time;
            this.action = action;
            this.scheduler = scheduler;
        }
        
        @Override
        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", this.time, this.action.toString());
        }
    }
}
