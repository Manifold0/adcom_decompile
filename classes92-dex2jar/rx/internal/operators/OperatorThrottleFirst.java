// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorThrottleFirst<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    final long timeInMilliseconds;
    
    public OperatorThrottleFirst(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        this.timeInMilliseconds = timeUnit.toMillis(n);
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            private long lastOnNext = -1L;
            
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
                final long now = OperatorThrottleFirst.this.scheduler.now();
                if (this.lastOnNext == -1L || now - this.lastOnNext >= OperatorThrottleFirst.this.timeInMilliseconds) {
                    this.lastOnNext = now;
                    subscriber.onNext(t);
                }
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
    }
}
