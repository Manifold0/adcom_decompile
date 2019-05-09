// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.Subscription;
import rx.Producer;
import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import rx.subscriptions.SerialSubscription;
import rx.internal.producers.ProducerArbiter;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorOnErrorResumeNextViaFunction<T> implements Operator<T, T>
{
    final Func1<? super Throwable, ? extends Observable<? extends T>> resumeFunction;
    
    public OperatorOnErrorResumeNextViaFunction(final Func1<? super Throwable, ? extends Observable<? extends T>> resumeFunction) {
        this.resumeFunction = resumeFunction;
    }
    
    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<T>(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(final Throwable t) {
                if (t instanceof Exception) {
                    return observable;
                }
                return Observable.error(t);
            }
        });
    }
    
    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<T>(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(final Throwable t) {
                return observable;
            }
        });
    }
    
    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(final Func1<? super Throwable, ? extends T> func1) {
        return new OperatorOnErrorResumeNextViaFunction<T>(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(final Throwable t) {
                return Observable.just(func1.call(t));
            }
        });
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ProducerArbiter producer = new ProducerArbiter();
        final SerialSubscription serialSubscription = new SerialSubscription();
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            private boolean done;
            long produced;
            
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
                    RxJavaHooks.onError(t);
                    return;
                }
                this.done = true;
                try {
                    this.unsubscribe();
                    final Subscriber<T> subscriber = new Subscriber<T>() {
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
                        
                        @Override
                        public void setProducer(final Producer producer) {
                            producer.setProducer(producer);
                        }
                    };
                    serialSubscription.set(subscriber);
                    final long produced = this.produced;
                    if (produced != 0L) {
                        producer.produced(produced);
                    }
                    ((Observable)OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(t)).unsafeSubscribe(subscriber);
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, subscriber);
                }
            }
            
            @Override
            public void onNext(final T t) {
                if (this.done) {
                    return;
                }
                ++this.produced;
                subscriber.onNext(t);
            }
            
            @Override
            public void setProducer(final Producer producer) {
                producer.setProducer(producer);
            }
        };
        serialSubscription.set(subscriber2);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producer);
        return subscriber2;
    }
}
