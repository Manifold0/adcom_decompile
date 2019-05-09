// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Subscription;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.internal.producers.SingleDelayedProducer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorAll<T> implements Operator<Boolean, T>
{
    final Func1<? super T, Boolean> predicate;
    
    public OperatorAll(final Func1<? super T, Boolean> predicate) {
        this.predicate = predicate;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer producer = new SingleDelayedProducer((Subscriber<? super T>)subscriber);
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            boolean done;
            
            @Override
            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    producer.setValue(true);
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (!this.done) {
                    this.done = true;
                    subscriber.onError(t);
                    return;
                }
                RxJavaHooks.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (!this.done) {
                    try {
                        if (!OperatorAll.this.predicate.call((Object)t)) {
                            this.done = true;
                            producer.setValue(false);
                            this.unsubscribe();
                        }
                    }
                    catch (Throwable t2) {
                        Exceptions.throwOrReport(t2, this, t);
                    }
                }
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(producer);
        return subscriber2;
    }
}
