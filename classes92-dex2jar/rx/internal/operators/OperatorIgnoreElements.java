// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public class OperatorIgnoreElements<T> implements Operator<T, T>
{
    OperatorIgnoreElements() {
    }
    
    public static <T> OperatorIgnoreElements<T> instance() {
        return (OperatorIgnoreElements<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
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
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
    
    static final class Holder
    {
        static final OperatorIgnoreElements<?> INSTANCE;
        
        static {
            INSTANCE = new OperatorIgnoreElements<Object>();
        }
    }
}
