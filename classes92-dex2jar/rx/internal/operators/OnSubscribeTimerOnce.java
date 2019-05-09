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

public final class OnSubscribeTimerOnce implements OnSubscribe<Long>
{
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    
    public OnSubscribeTimerOnce(final long time, final TimeUnit unit, final Scheduler scheduler) {
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public void call(final Subscriber<? super Long> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        worker.schedule(new Action0() {
            @Override
            public void call() {
                try {
                    subscriber.onNext(0L);
                    subscriber.onCompleted();
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, subscriber);
                }
            }
        }, this.time, this.unit);
    }
}
