// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OnSubscribeTimerPeriodically implements OnSubscribe<Long>
{
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;
    
    public OnSubscribeTimerPeriodically(final long initialDelay, final long period, final TimeUnit unit, final Scheduler scheduler) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public void call(final Subscriber<? super Long> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        worker.schedulePeriodically(new Action0() {
            long counter;
            
            @Override
            public void call() {
                try {
                    final Subscriber val$child = subscriber;
                    final long counter = this.counter;
                    this.counter = 1L + counter;
                    val$child.onNext(counter);
                }
                catch (Throwable t) {
                    try {
                        worker.unsubscribe();
                    }
                    finally {
                        Exceptions.throwOrReport(t, subscriber);
                    }
                }
            }
        }, this.initialDelay, this.period, this.unit);
    }
}
