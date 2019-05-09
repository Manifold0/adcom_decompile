// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.exceptions.OnErrorFailedException;
import rx.exceptions.CompositeException;
import rx.plugins.RxJavaHooks;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.Exceptions;
import rx.annotations.Experimental;
import rx.Subscription;
import rx.CompletableSubscriber;

@Experimental
public final class SafeCompletableSubscriber implements CompletableSubscriber, Subscription
{
    final CompletableSubscriber actual;
    boolean done;
    Subscription s;
    
    public SafeCompletableSubscriber(final CompletableSubscriber actual) {
        this.actual = actual;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.done || this.s.isUnsubscribed();
    }
    
    @Override
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            throw new OnCompletedFailedException(t);
        }
    }
    
    @Override
    public void onError(final Throwable t) {
        RxJavaHooks.onError(t);
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onError(t);
        }
        catch (Throwable t2) {
            Exceptions.throwIfFatal(t2);
            throw new OnErrorFailedException(new CompositeException(new Throwable[] { t, t2 }));
        }
    }
    
    @Override
    public void onSubscribe(final Subscription s) {
        this.s = s;
        try {
            this.actual.onSubscribe(this);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            s.unsubscribe();
            this.onError(t);
        }
    }
    
    @Override
    public void unsubscribe() {
        this.s.unsubscribe();
    }
}
