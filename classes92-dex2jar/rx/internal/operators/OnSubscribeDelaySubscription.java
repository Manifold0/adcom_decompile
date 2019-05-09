// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.observers.Subscribers;
import rx.functions.Action0;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OnSubscribeDelaySubscription<T> implements OnSubscribe<T>
{
    final Scheduler scheduler;
    final Observable<? extends T> source;
    final long time;
    final TimeUnit unit;
    
    public OnSubscribeDelaySubscription(final Observable<? extends T> source, final long time, final TimeUnit unit, final Scheduler scheduler) {
        this.source = source;
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        worker.schedule(new Action0() {
            @Override
            public void call() {
                if (!subscriber.isUnsubscribed()) {
                    OnSubscribeDelaySubscription.this.source.unsafeSubscribe(Subscribers.wrap((Subscriber<? super T>)subscriber));
                }
            }
        }, this.time, this.unit);
    }
}
