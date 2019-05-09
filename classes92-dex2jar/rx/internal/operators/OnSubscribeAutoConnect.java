// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.observers.Subscribers;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.Subscription;
import rx.functions.Action1;
import rx.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public final class OnSubscribeAutoConnect<T> extends AtomicInteger implements OnSubscribe<T>
{
    final Action1<? super Subscription> connection;
    final int numberOfSubscribers;
    final ConnectableObservable<? extends T> source;
    
    public OnSubscribeAutoConnect(final ConnectableObservable<? extends T> source, final int numberOfSubscribers, final Action1<? super Subscription> connection) {
        if (numberOfSubscribers <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.source = source;
        this.numberOfSubscribers = numberOfSubscribers;
        this.connection = connection;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(Subscribers.wrap((Subscriber<? super Object>)subscriber));
        if (this.incrementAndGet() == this.numberOfSubscribers) {
            this.source.connect(this.connection);
        }
    }
}
