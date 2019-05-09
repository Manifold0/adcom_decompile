// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.observers.Subscribers;
import rx.subscriptions.Subscriptions;
import rx.Subscriber;
import rx.functions.Action0;
import rx.Observable;

public class OperatorDoOnUnsubscribe<T> implements Operator<T, T>
{
    private final Action0 unsubscribe;
    
    public OperatorDoOnUnsubscribe(final Action0 unsubscribe) {
        this.unsubscribe = unsubscribe;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        subscriber.add(Subscriptions.create(this.unsubscribe));
        return Subscribers.wrap(subscriber);
    }
}
