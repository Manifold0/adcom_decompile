// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Action0;
import rx.Observable;

public final class OperatorDoAfterTerminate<T> implements Operator<T, T>
{
    final Action0 action;
    
    public OperatorDoAfterTerminate(final Action0 action) {
        if (action == null) {
            throw new NullPointerException("Action can not be null");
        }
        this.action = action;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            void callAction() {
                try {
                    OperatorDoAfterTerminate.this.action.call();
                }
                catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    RxJavaHooks.onError(t);
                }
            }
            
            @Override
            public void onCompleted() {
                try {
                    subscriber.onCompleted();
                }
                finally {
                    this.callAction();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                try {
                    subscriber.onError(t);
                }
                finally {
                    this.callAction();
                }
            }
            
            @Override
            public void onNext(final T t) {
                subscriber.onNext(t);
            }
        };
    }
}
