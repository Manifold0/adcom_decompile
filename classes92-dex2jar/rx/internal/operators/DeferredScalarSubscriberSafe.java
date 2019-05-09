// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.Subscriber;

public abstract class DeferredScalarSubscriberSafe<T, R> extends DeferredScalarSubscriber<T, R>
{
    protected boolean done;
    
    public DeferredScalarSubscriberSafe(final Subscriber<? super R> subscriber) {
        super(subscriber);
    }
    
    @Override
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        super.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        if (!this.done) {
            this.done = true;
            super.onError(t);
            return;
        }
        RxJavaHooks.onError(t);
    }
}
