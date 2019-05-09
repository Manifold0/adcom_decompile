// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import rx.Subscription;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.Single;

public final class SingleDoAfterTerminate<T> implements OnSubscribe<T>
{
    final Action0 action;
    final Single<T> source;
    
    public SingleDoAfterTerminate(final Single<T> source, final Action0 action) {
        this.source = source;
        this.action = action;
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleDoAfterTerminateSubscriber<Object> singleDoAfterTerminateSubscriber = new SingleDoAfterTerminateSubscriber<Object>((SingleSubscriber<? super Object>)singleSubscriber, this.action);
        singleSubscriber.add(singleDoAfterTerminateSubscriber);
        this.source.subscribe(singleDoAfterTerminateSubscriber);
    }
    
    static final class SingleDoAfterTerminateSubscriber<T> extends SingleSubscriber<T>
    {
        final Action0 action;
        final SingleSubscriber<? super T> actual;
        
        public SingleDoAfterTerminateSubscriber(final SingleSubscriber<? super T> actual, final Action0 action) {
            this.actual = actual;
            this.action = action;
        }
        
        void doAction() {
            try {
                this.action.call();
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaHooks.onError(t);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            try {
                this.actual.onError(t);
            }
            finally {
                this.doAction();
            }
        }
        
        @Override
        public void onSuccess(final T t) {
            try {
                this.actual.onSuccess((Object)t);
            }
            finally {
                this.doAction();
            }
        }
    }
}
