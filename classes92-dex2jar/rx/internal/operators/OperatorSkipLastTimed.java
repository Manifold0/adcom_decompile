// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.ArrayDeque;
import rx.schedulers.Timestamped;
import java.util.Deque;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public class OperatorSkipLastTimed<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    final long timeInMillis;
    
    public OperatorSkipLastTimed(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        this.timeInMillis = timeUnit.toMillis(n);
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            private Deque<Timestamped<T>> buffer = new ArrayDeque<Timestamped<T>>();
            
            private void emitItemsOutOfWindow(final long n) {
                final long timeInMillis = OperatorSkipLastTimed.this.timeInMillis;
                while (!this.buffer.isEmpty()) {
                    final Timestamped<T> timestamped = this.buffer.getFirst();
                    if (timestamped.getTimestampMillis() >= n - timeInMillis) {
                        break;
                    }
                    this.buffer.removeFirst();
                    subscriber.onNext(timestamped.getValue());
                }
            }
            
            @Override
            public void onCompleted() {
                this.emitItemsOutOfWindow(OperatorSkipLastTimed.this.scheduler.now());
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                final long now = OperatorSkipLastTimed.this.scheduler.now();
                this.emitItemsOutOfWindow(now);
                this.buffer.offerLast(new Timestamped<T>(now, t));
            }
        };
    }
}
