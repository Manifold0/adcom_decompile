// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import rx.Subscriber;
import rx.Observable;

public class OperatorSkipLast<T> implements Operator<T, T>
{
    final int count;
    
    public OperatorSkipLast(final int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.count = count;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            private final Deque<Object> deque = new ArrayDeque<Object>();
            private final NotificationLite<T> on = NotificationLite.instance();
            
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
                if (OperatorSkipLast.this.count == 0) {
                    subscriber.onNext(t);
                    return;
                }
                if (this.deque.size() == OperatorSkipLast.this.count) {
                    subscriber.onNext(this.on.getValue(this.deque.removeFirst()));
                }
                else {
                    this.request(1L);
                }
                this.deque.offerLast(this.on.next(t));
            }
        };
    }
}
