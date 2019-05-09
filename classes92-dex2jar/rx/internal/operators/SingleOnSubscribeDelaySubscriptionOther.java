// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.SingleSubscriber;
import rx.Observable;
import rx.Single;

public final class SingleOnSubscribeDelaySubscriptionOther<T> implements OnSubscribe<T>
{
    final Single<? extends T> main;
    final Observable<?> other;
    
    public SingleOnSubscribeDelaySubscriptionOther(final Single<? extends T> main, final Observable<?> other) {
        this.main = main;
        this.other = other;
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() {
            @Override
            public void onError(final Throwable t) {
                singleSubscriber.onError(t);
            }
            
            @Override
            public void onSuccess(final T t) {
                singleSubscriber.onSuccess(t);
            }
        };
        final SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        final Subscriber<Object> subscriber = new Subscriber<Object>() {
            boolean done;
            
            @Override
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(singleSubscriber2);
                SingleOnSubscribeDelaySubscriptionOther.this.main.subscribe(singleSubscriber2);
            }
            
            @Override
            public void onError(final Throwable t) {
                if (this.done) {
                    RxJavaHooks.onError(t);
                    return;
                }
                this.done = true;
                singleSubscriber2.onError(t);
            }
            
            @Override
            public void onNext(final Object o) {
                this.onCompleted();
            }
        };
        serialSubscription.set(subscriber);
        this.other.subscribe(subscriber);
    }
}
