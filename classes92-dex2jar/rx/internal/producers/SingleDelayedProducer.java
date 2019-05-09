// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.Producer;
import java.util.concurrent.atomic.AtomicInteger;

public final class SingleDelayedProducer<T> extends AtomicInteger implements Producer
{
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 2;
    static final int NO_REQUEST_HAS_VALUE = 1;
    static final int NO_REQUEST_NO_VALUE = 0;
    private static final long serialVersionUID = -2873467947112093874L;
    final Subscriber<? super T> child;
    T value;
    
    public SingleDelayedProducer(final Subscriber<? super T> child) {
        this.child = child;
    }
    
    private static <T> void emit(final Subscriber<? super T> subscriber, final T t) {
        if (!subscriber.isUnsubscribed()) {
            try {
                subscriber.onNext(t);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
            catch (Throwable t2) {
                Exceptions.throwOrReport(t2, subscriber, t);
            }
        }
    }
    
    @Override
    public void request(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (n != 0L) {
            int value;
            while (true) {
                value = this.get();
                if (value != 0) {
                    break;
                }
                if (!this.compareAndSet(0, 2)) {
                    continue;
                }
                return;
            }
            if (value == 1 && this.compareAndSet(1, 3)) {
                emit(this.child, this.value);
            }
        }
    }
    
    public void setValue(final T value) {
        do {
            final int value2 = this.get();
            if (value2 == 0) {
                this.value = value;
            }
            else {
                if (value2 == 2 && this.compareAndSet(2, 3)) {
                    emit(this.child, value);
                    return;
                }
                break;
            }
        } while (!this.compareAndSet(0, 1));
    }
}
