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

public final class OperatorAny<T> implements Operator<Boolean, T>
{
    final Func1<? super T, Boolean> predicate;
    final boolean returnOnEmpty;
    
    public OperatorAny(final Func1<? super T, Boolean> predicate, final boolean returnOnEmpty) {
        this.predicate = predicate;
        this.returnOnEmpty = returnOnEmpty;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer producer = new SingleDelayedProducer((Subscriber<? super T>)subscriber);
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            boolean done;
            boolean hasElements;
            
            @Override
            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    if (!this.hasElements) {
                        producer.setValue(OperatorAny.this.returnOnEmpty);
                        return;
                    }
                    producer.setValue(false);
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
            public void onNext(T val$producer) {
                if (!this.done) {
                    while (true) {
                        this.hasElements = true;
                        while (true) {
                            try {
                                if (!OperatorAny.this.predicate.call((Object)val$producer)) {
                                    break;
                                }
                                this.done = true;
                                val$producer = (T)producer;
                                if (!OperatorAny.this.returnOnEmpty) {
                                    final boolean b = true;
                                    ((SingleDelayedProducer<Boolean>)val$producer).setValue(Boolean.valueOf(b));
                                    this.unsubscribe();
                                    return;
                                }
                            }
                            catch (Throwable t) {
                                Exceptions.throwOrReport(t, this, val$producer);
                                return;
                            }
                            final boolean b = false;
                            continue;
                        }
                    }
                }
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(producer);
        return subscriber2;
    }
}
