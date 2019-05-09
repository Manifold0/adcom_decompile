// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeLift<T, R> implements OnSubscribe<R>
{
    final Operator<? extends R, ? super T> operator;
    final OnSubscribe<T> parent;
    
    public OnSubscribeLift(final OnSubscribe<T> parent, final Operator<? extends R, ? super T> operator) {
        this.parent = parent;
        this.operator = operator;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        try {
            final Subscriber<? super T> subscriber2 = RxJavaHooks.onObservableLift(this.operator).call(subscriber);
            try {
                subscriber2.onStart();
                this.parent.call(subscriber2);
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                subscriber2.onError(t);
            }
        }
        catch (Throwable t2) {
            Exceptions.throwIfFatal(t2);
            subscriber.onError(t2);
        }
    }
}
