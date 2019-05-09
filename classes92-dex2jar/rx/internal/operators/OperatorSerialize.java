// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSerialize<T> implements Operator<T, T>
{
    OperatorSerialize() {
    }
    
    public static <T> OperatorSerialize<T> instance() {
        return (OperatorSerialize<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new SerializedSubscriber<Object>(new Subscriber<T>(subscriber) {
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
                subscriber.onNext(t);
            }
        });
    }
    
    static final class Holder
    {
        static final OperatorSerialize<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorSerialize<Object>();
        }
    }
}
