// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.Producer;
import rx.Subscriber;
import rx.Observable;

public final class OperatorElementAt<T> implements Operator<T, T>
{
    final T defaultValue;
    final boolean hasDefault;
    final int index;
    
    public OperatorElementAt(final int n) {
        this(n, null, false);
    }
    
    public OperatorElementAt(final int n, final T t) {
        this(n, t, true);
    }
    
    private OperatorElementAt(final int index, final T defaultValue, final boolean hasDefault) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
        this.index = index;
        this.defaultValue = defaultValue;
        this.hasDefault = hasDefault;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            private int currentIndex;
            
            @Override
            public void onCompleted() {
                if (this.currentIndex <= OperatorElementAt.this.index) {
                    if (!OperatorElementAt.this.hasDefault) {
                        subscriber.onError(new IndexOutOfBoundsException(OperatorElementAt.this.index + " is out of bounds"));
                        return;
                    }
                    subscriber.onNext(OperatorElementAt.this.defaultValue);
                    subscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (this.currentIndex++ == OperatorElementAt.this.index) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                    this.unsubscribe();
                }
            }
            
            @Override
            public void setProducer(final Producer producer) {
                subscriber.setProducer(new InnerProducer(producer));
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
    
    static class InnerProducer extends AtomicBoolean implements Producer
    {
        private static final long serialVersionUID = 1L;
        final Producer actual;
        
        public InnerProducer(final Producer actual) {
            this.actual = actual;
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (n > 0L && this.compareAndSet(false, true)) {
                this.actual.request(Long.MAX_VALUE);
            }
        }
    }
}
