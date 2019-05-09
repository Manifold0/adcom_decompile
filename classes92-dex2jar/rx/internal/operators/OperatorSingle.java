// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import java.util.NoSuchElementException;
import rx.Producer;
import rx.internal.producers.SingleProducer;
import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSingle<T> implements Operator<T, T>
{
    private final T defaultValue;
    private final boolean hasDefaultValue;
    
    OperatorSingle() {
        this(false, null);
    }
    
    public OperatorSingle(final T t) {
        this(true, t);
    }
    
    private OperatorSingle(final boolean hasDefaultValue, final T defaultValue) {
        this.hasDefaultValue = hasDefaultValue;
        this.defaultValue = defaultValue;
    }
    
    public static <T> OperatorSingle<T> instance() {
        return (OperatorSingle<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ParentSubscriber<Object> parentSubscriber = new ParentSubscriber<Object>((Subscriber<? super Object>)subscriber, this.hasDefaultValue, this.defaultValue);
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }
    
    static final class Holder
    {
        static final OperatorSingle<?> INSTANCE;
        
        static {
            INSTANCE = new OperatorSingle<Object>();
        }
    }
    
    static final class ParentSubscriber<T> extends Subscriber<T>
    {
        private final Subscriber<? super T> child;
        private final T defaultValue;
        private final boolean hasDefaultValue;
        private boolean hasTooManyElements;
        private boolean isNonEmpty;
        private T value;
        
        ParentSubscriber(final Subscriber<? super T> child, final boolean hasDefaultValue, final T defaultValue) {
            this.child = child;
            this.hasDefaultValue = hasDefaultValue;
            this.defaultValue = defaultValue;
            this.request(2L);
        }
        
        @Override
        public void onCompleted() {
            if (!this.hasTooManyElements) {
                if (this.isNonEmpty) {
                    this.child.setProducer(new SingleProducer<Object>(this.child, this.value));
                }
                else {
                    if (this.hasDefaultValue) {
                        this.child.setProducer(new SingleProducer<Object>(this.child, this.defaultValue));
                        return;
                    }
                    this.child.onError(new NoSuchElementException("Sequence contains no elements"));
                }
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.hasTooManyElements) {
                RxJavaHooks.onError(t);
                return;
            }
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final T value) {
            if (!this.hasTooManyElements) {
                if (!this.isNonEmpty) {
                    this.value = value;
                    this.isNonEmpty = true;
                    return;
                }
                this.hasTooManyElements = true;
                this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
                this.unsubscribe();
            }
        }
    }
}
