// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Observable;

public final class OperatorAsObservable<T> implements Operator<T, T>
{
    OperatorAsObservable() {
    }
    
    public static <T> OperatorAsObservable<T> instance() {
        return (OperatorAsObservable<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return subscriber;
    }
    
    static final class Holder
    {
        static final OperatorAsObservable<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorAsObservable<Object>();
        }
    }
}
