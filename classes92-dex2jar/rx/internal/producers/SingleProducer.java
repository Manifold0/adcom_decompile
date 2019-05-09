// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.Producer;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleProducer<T> extends AtomicBoolean implements Producer
{
    private static final long serialVersionUID = -3353584923995471404L;
    final Subscriber<? super T> child;
    final T value;
    
    public SingleProducer(final Subscriber<? super T> child, final T value) {
        this.child = child;
        this.value = value;
    }
    
    @Override
    public void request(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (n != 0L && this.compareAndSet(false, true)) {
            final Subscriber<? super T> child = this.child;
            if (!child.isUnsubscribed()) {
                final T value = this.value;
                try {
                    child.onNext(value);
                    if (!child.isUnsubscribed()) {
                        child.onCompleted();
                    }
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, child, value);
                }
            }
        }
    }
}
