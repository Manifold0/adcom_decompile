// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.internal.subscriptions.SequentialSubscription;
import rx.functions.Action0;
import rx.annotations.Experimental;
import rx.internal.schedulers.SchedulerWhen;
import rx.functions.Func1;
import java.util.concurrent.TimeUnit;

public abstract class Scheduler
{
    static final long CLOCK_DRIFT_TOLERANCE_NANOS;
    
    static {
        CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15L));
    }
    
    public abstract Worker createWorker();
    
    public long now() {
        return System.currentTimeMillis();
    }
    
    @Experimental
    public <S extends rx.Scheduler> S when(final Func1<Observable<Observable<Completable>>, Completable> func1) {
        return (S)new SchedulerWhen(func1, this);
    }
    
    public abstract static class Worker implements Subscription
    {
        public long now() {
            return System.currentTimeMillis();
        }
        
        public abstract Subscription schedule(final Action0 p0);
        
        public abstract Subscription schedule(final Action0 p0, final long p1, final TimeUnit p2);
        
        public Subscription schedulePeriodically(final Action0 action0, final long n, long nanos, final TimeUnit timeUnit) {
            nanos = timeUnit.toNanos(nanos);
            final long nanos2 = TimeUnit.MILLISECONDS.toNanos(this.now());
            final long nanos3 = timeUnit.toNanos(n);
            final SequentialSubscription sequentialSubscription = new SequentialSubscription();
            final SequentialSubscription sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
            sequentialSubscription.replace(this.schedule(new Action0() {
                long count;
                long lastNowNanos = nanos2;
                long startInNanos = this.val$firstStartInNanos;
                final /* synthetic */ long val$firstStartInNanos = nanos2 + nanos3;
                
                @Override
                public void call() {
                    action0.call();
                    if (!sequentialSubscription2.isUnsubscribed()) {
                        final long nanos = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                        long n;
                        if (Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS + nanos < this.lastNowNanos || nanos >= this.lastNowNanos + nanos + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS) {
                            n = nanos + nanos;
                            final long val$periodInNanos = nanos;
                            final long count = this.count + 1L;
                            this.count = count;
                            this.startInNanos = n - val$periodInNanos * count;
                        }
                        else {
                            final long startInNanos = this.startInNanos;
                            final long count2 = this.count + 1L;
                            this.count = count2;
                            n = startInNanos + count2 * nanos;
                        }
                        this.lastNowNanos = nanos;
                        sequentialSubscription2.replace(Worker.this.schedule(this, n - nanos, TimeUnit.NANOSECONDS));
                    }
                }
            }, n, timeUnit));
            return sequentialSubscription2;
        }
    }
}
