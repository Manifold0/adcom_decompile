// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observable;
import rx.Subscription;
import rx.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscriber;

public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T>
{
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 1;
    static final int NO_REQUEST_HAS_VALUE = 2;
    static final int NO_REQUEST_NO_VALUE = 0;
    protected final Subscriber<? super R> actual;
    protected boolean hasValue;
    final AtomicInteger state;
    protected R value;
    
    public DeferredScalarSubscriber(final Subscriber<? super R> actual) {
        this.actual = actual;
        this.state = new AtomicInteger();
    }
    
    protected final void complete() {
        this.actual.onCompleted();
    }
    
    protected final void complete(final R value) {
        final Subscriber<? super R> actual = this.actual;
        do {
            final int value2 = this.state.get();
            if (value2 == 2 || value2 == 3 || actual.isUnsubscribed()) {
                return;
            }
            if (value2 == 1) {
                actual.onNext(value);
                if (!actual.isUnsubscribed()) {
                    actual.onCompleted();
                }
                this.state.lazySet(3);
                return;
            }
            this.value = value;
        } while (!this.state.compareAndSet(0, 2));
    }
    
    final void downstreamRequest(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        }
        if (n != 0L) {
            final Subscriber<? super R> actual = this.actual;
            do {
                final int value = this.state.get();
                if (value == 1 || value == 3 || actual.isUnsubscribed()) {
                    return;
                }
                if (value != 2) {
                    continue;
                }
                if (!this.state.compareAndSet(2, 3)) {
                    return;
                }
                actual.onNext(this.value);
                if (!actual.isUnsubscribed()) {
                    actual.onCompleted();
                }
            } while (!this.state.compareAndSet(0, 1));
        }
    }
    
    @Override
    public void onCompleted() {
        if (this.hasValue) {
            this.complete(this.value);
            return;
        }
        this.complete();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.value = null;
        this.actual.onError(t);
    }
    
    @Override
    public final void setProducer(final Producer producer) {
        producer.request(Long.MAX_VALUE);
    }
    
    final void setupDownstream() {
        final Subscriber<? super R> actual = this.actual;
        actual.add(this);
        actual.setProducer(new InnerProducer(this));
    }
    
    public final void subscribeTo(final Observable<? extends T> observable) {
        this.setupDownstream();
        observable.unsafeSubscribe(this);
    }
    
    static final class InnerProducer implements Producer
    {
        final DeferredScalarSubscriber<?, ?> parent;
        
        public InnerProducer(final DeferredScalarSubscriber<?, ?> parent) {
            this.parent = parent;
        }
        
        @Override
        public void request(final long n) {
            this.parent.downstreamRequest(n);
        }
    }
}
