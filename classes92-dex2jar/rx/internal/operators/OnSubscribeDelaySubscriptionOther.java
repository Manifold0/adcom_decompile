// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;
import rx.observers.Subscribers;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeDelaySubscriptionOther<T, U> implements OnSubscribe<T>
{
    final Observable<? extends T> main;
    final Observable<U> other;
    
    public OnSubscribeDelaySubscriptionOther(final Observable<? extends T> main, final Observable<U> other) {
        this.main = main;
        this.other = other;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final Subscriber<U> subscriber2 = new Subscriber<U>() {
            boolean done;
            final /* synthetic */ Subscriber val$child = Subscribers.wrap((Subscriber<? super Object>)subscriber);
            
            @Override
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(Subscriptions.unsubscribed());
                OnSubscribeDelaySubscriptionOther.this.main.unsafeSubscribe(this.val$child);
            }
            
            @Override
            public void onError(final Throwable t) {
                if (this.done) {
                    RxJavaHooks.onError(t);
                    return;
                }
                this.done = true;
                this.val$child.onError(t);
            }
            
            @Override
            public void onNext(final U u) {
                this.onCompleted();
            }
        };
        serialSubscription.set(subscriber2);
        this.other.unsafeSubscribe(subscriber2);
    }
}
