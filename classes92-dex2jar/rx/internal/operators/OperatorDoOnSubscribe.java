// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.observers.Subscribers;
import rx.Subscriber;
import rx.functions.Action0;
import rx.Observable;

public class OperatorDoOnSubscribe<T> implements Operator<T, T>
{
    private final Action0 subscribe;
    
    public OperatorDoOnSubscribe(final Action0 subscribe) {
        this.subscribe = subscribe;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        this.subscribe.call();
        return Subscribers.wrap(subscriber);
    }
}
