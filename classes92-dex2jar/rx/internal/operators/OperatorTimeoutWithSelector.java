// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.Subscription;
import rx.Scheduler;
import rx.functions.Func1;
import rx.Observable;
import rx.functions.Func0;

public class OperatorTimeoutWithSelector<T, U, V> extends OperatorTimeoutBase<T>
{
    public OperatorTimeoutWithSelector(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func2, final Observable<? extends T> observable) {
        super((FirstTimeoutStub)new FirstTimeoutStub<T>() {
            @Override
            public Subscription call(final TimeoutSubscriber<T> timeoutSubscriber, final Long n, final Scheduler.Worker worker) {
                if (func0 != null) {
                    try {
                        return func0.call().unsafeSubscribe(new Subscriber<U>() {
                            @Override
                            public void onCompleted() {
                                timeoutSubscriber.onTimeout(n);
                            }
                            
                            @Override
                            public void onError(final Throwable t) {
                                timeoutSubscriber.onError(t);
                            }
                            
                            @Override
                            public void onNext(final U u) {
                                timeoutSubscriber.onTimeout(n);
                            }
                        });
                    }
                    catch (Throwable t) {
                        Exceptions.throwOrReport(t, timeoutSubscriber);
                        return Subscriptions.unsubscribed();
                    }
                }
                return Subscriptions.unsubscribed();
            }
        }, (TimeoutStub)new TimeoutStub<T>() {
            @Override
            public Subscription call(final TimeoutSubscriber<T> timeoutSubscriber, final Long n, final T t, final Scheduler.Worker worker) {
                try {
                    return func2.call(t).unsafeSubscribe(new Subscriber<V>() {
                        @Override
                        public void onCompleted() {
                            timeoutSubscriber.onTimeout(n);
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            timeoutSubscriber.onError(t);
                        }
                        
                        @Override
                        public void onNext(final V v) {
                            timeoutSubscriber.onTimeout(n);
                        }
                    });
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, timeoutSubscriber);
                    return Subscriptions.unsubscribed();
                }
            }
        }, observable, Schedulers.immediate());
    }
}
