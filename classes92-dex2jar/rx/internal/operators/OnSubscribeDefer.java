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

public final class OnSubscribeDefer<T> implements OnSubscribe<T>
{
    final Func0<? extends Observable<? extends T>> observableFactory;
    
    public OnSubscribeDefer(final Func0<? extends Observable<? extends T>> observableFactory) {
        this.observableFactory = observableFactory;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        try {
            ((Observable)this.observableFactory.call()).unsafeSubscribe(Subscribers.wrap((Subscriber<? super T>)subscriber));
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
        }
    }
}
