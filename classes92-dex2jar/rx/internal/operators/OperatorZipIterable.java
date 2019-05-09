// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.observers.Subscribers;
import rx.Subscriber;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorZipIterable<T1, T2, R> implements Operator<R, T1>
{
    final Iterable<? extends T2> iterable;
    final Func2<? super T1, ? super T2, ? extends R> zipFunction;
    
    public OperatorZipIterable(final Iterable<? extends T2> iterable, final Func2<? super T1, ? super T2, ? extends R> zipFunction) {
        this.iterable = iterable;
        this.zipFunction = zipFunction;
    }
    
    @Override
    public Subscriber<? super T1> call(final Subscriber<? super R> subscriber) {
        final Iterator<? extends T2> iterator = this.iterable.iterator();
        try {
            if (!iterator.hasNext()) {
                subscriber.onCompleted();
                return Subscribers.empty();
            }
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
            return Subscribers.empty();
        }
        return new Subscriber<T1>(subscriber) {
            boolean done;
            
            @Override
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                if (this.done) {
                    Exceptions.throwIfFatal(t);
                    return;
                }
                this.done = true;
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T1 t1) {
                if (!this.done) {
                    try {
                        subscriber.onNext(OperatorZipIterable.this.zipFunction.call((Object)t1, iterator.next()));
                        if (!iterator.hasNext()) {
                            this.onCompleted();
                        }
                    }
                    catch (Throwable t2) {
                        Exceptions.throwOrReport(t2, this);
                    }
                }
            }
        };
    }
}
