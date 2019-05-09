// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorSkipWhile<T> implements Operator<T, T>
{
    final Func2<? super T, Integer, Boolean> predicate;
    
    public OperatorSkipWhile(final Func2<? super T, Integer, Boolean> predicate) {
        this.predicate = predicate;
    }
    
    public static <T> Func2<T, Integer, Boolean> toPredicate2(final Func1<? super T, Boolean> func1) {
        return new Func2<T, Integer, Boolean>() {
            @Override
            public Boolean call(final T t, final Integer n) {
                return func1.call(t);
            }
        };
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            int index;
            boolean skipping = true;
            
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
                if (!this.skipping) {
                    subscriber.onNext(t);
                    return;
                }
                try {
                    if (!OperatorSkipWhile.this.predicate.call((Object)t, Integer.valueOf(this.index++))) {
                        this.skipping = false;
                        subscriber.onNext(t);
                        return;
                    }
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, subscriber, t);
                    return;
                }
                this.request(1L);
            }
        };
    }
}
