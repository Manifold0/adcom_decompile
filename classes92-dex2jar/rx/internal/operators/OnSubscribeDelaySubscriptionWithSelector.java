// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.observers.Subscribers;
import rx.Subscriber;
import rx.functions.Func0;
import rx.Observable;

public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements OnSubscribe<T>
{
    final Observable<? extends T> source;
    final Func0<? extends Observable<U>> subscriptionDelay;
    
    public OnSubscribeDelaySubscriptionWithSelector(final Observable<? extends T> source, final Func0<? extends Observable<U>> subscriptionDelay) {
        this.source = source;
        this.subscriptionDelay = subscriptionDelay;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        try {
            ((Observable)this.subscriptionDelay.call()).take(1).unsafeSubscribe(new Subscriber<U>() {
                @Override
                public void onCompleted() {
                    OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap((Subscriber<? super T>)subscriber));
                }
                
                @Override
                public void onError(final Throwable t) {
                    subscriber.onError(t);
                }
                
                @Override
                public void onNext(final U u) {
                }
            });
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
        }
    }
}
