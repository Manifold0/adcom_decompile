// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Scheduler;
import rx.schedulers.TimeInterval;
import rx.Observable;

public final class OperatorTimeInterval<T> implements Operator<TimeInterval<T>, T>
{
    final Scheduler scheduler;
    
    public OperatorTimeInterval(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super TimeInterval<T>> subscriber) {
        return new Subscriber<T>(subscriber) {
            private long lastTimestamp = OperatorTimeInterval.this.scheduler.now();
            
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                final long now = OperatorTimeInterval.this.scheduler.now();
                subscriber.onNext(new TimeInterval(now - this.lastTimestamp, t));
                this.lastTimestamp = now;
            }
        };
    }
}
