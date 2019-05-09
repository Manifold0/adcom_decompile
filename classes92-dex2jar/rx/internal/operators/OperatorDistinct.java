// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.internal.util.UtilityFunctions;
import java.util.HashSet;
import java.util.Set;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorDistinct<T, U> implements Operator<T, T>
{
    final Func1<? super T, ? extends U> keySelector;
    
    public OperatorDistinct(final Func1<? super T, ? extends U> keySelector) {
        this.keySelector = keySelector;
    }
    
    public static <T> OperatorDistinct<T, T> instance() {
        return (OperatorDistinct<T, T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            Set<U> keyMemory = new HashSet<U>();
            
            @Override
            public void onCompleted() {
                this.keyMemory = null;
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                this.keyMemory = null;
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (this.keyMemory.add((U)OperatorDistinct.this.keySelector.call((Object)t))) {
                    subscriber.onNext(t);
                    return;
                }
                this.request(1L);
            }
        };
    }
    
    static final class Holder
    {
        static final OperatorDistinct<?, ?> INSTANCE;
        
        static {
            INSTANCE = new OperatorDistinct<Object, Object>(UtilityFunctions.identity());
        }
    }
}
