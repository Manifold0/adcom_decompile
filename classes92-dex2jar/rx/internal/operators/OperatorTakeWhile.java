// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorTakeWhile<T> implements Operator<T, T>
{
    final Func2<? super T, ? super Integer, Boolean> predicate;
    
    public OperatorTakeWhile(final Func1<? super T, Boolean> func1) {
        this(new Func2<T, Integer, Boolean>() {
            @Override
            public Boolean call(final T t, final Integer n) {
                return func1.call(t);
            }
        });
    }
    
    public OperatorTakeWhile(final Func2<? super T, ? super Integer, Boolean> predicate) {
        this.predicate = predicate;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber<T> subscriber2 = new Subscriber<T>(subscriber, false) {
            private int counter;
            private boolean done;
            
            @Override
            public void onCompleted() {
                if (!this.done) {
                    subscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (!this.done) {
                    subscriber.onError(t);
                }
            }
            
            @Override
            public void onNext(final T t) {
                try {
                    if (OperatorTakeWhile.this.predicate.call((Object)t, Integer.valueOf(this.counter++))) {
                        subscriber.onNext(t);
                        return;
                    }
                }
                catch (Throwable t2) {
                    this.done = true;
                    Exceptions.throwOrReport(t2, subscriber, t);
                    this.unsubscribe();
                    return;
                }
                this.done = true;
                subscriber.onCompleted();
                this.unsubscribe();
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
}
