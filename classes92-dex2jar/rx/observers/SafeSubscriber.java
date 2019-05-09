// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.Observer;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.Exceptions;
import rx.exceptions.UnsubscribeFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.Arrays;
import rx.plugins.RxJavaHooks;
import rx.Subscriber;

public class SafeSubscriber<T> extends Subscriber<T>
{
    private final Subscriber<? super T> actual;
    boolean done;
    
    public SafeSubscriber(final Subscriber<? super T> actual) {
        super(actual);
        this.actual = actual;
    }
    
    protected void _onError(final Throwable t) {
        RxJavaHooks.onError(t);
        try {
            this.actual.onError(t);
            final SafeSubscriber safeSubscriber = this;
            safeSubscriber.unsubscribe();
            return;
        }
        catch (OnErrorNotImplementedException ex) {
            try {
                this.unsubscribe();
                throw ex;
            }
            catch (Throwable t2) {
                RxJavaHooks.onError(t2);
                throw new OnErrorNotImplementedException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(t, t2)));
            }
        }
        catch (Throwable t3) {
            RxJavaHooks.onError(t3);
            try {
                this.unsubscribe();
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(t, t3)));
            }
            catch (Throwable t4) {
                RxJavaHooks.onError(t4);
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(t, t3, t4)));
            }
        }
        try {
            final SafeSubscriber safeSubscriber = this;
            safeSubscriber.unsubscribe();
        }
        catch (Throwable t) {
            RxJavaHooks.onError(t);
            throw new OnErrorFailedException(t);
        }
    }
    
    public Subscriber<? super T> getActual() {
        return this.actual;
    }
    
    @Override
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
            try {
                this.unsubscribe();
            }
            catch (Throwable t) {
                RxJavaHooks.onError(t);
                throw new UnsubscribeFailedException(t.getMessage(), t);
            }
        }
        catch (Throwable t2) {
            Exceptions.throwIfFatal(t2);
            RxJavaHooks.onError(t2);
            throw new OnCompletedFailedException(t2.getMessage(), t2);
        }
        finally {
            try {
                this.unsubscribe();
            }
            catch (Throwable t3) {
                RxJavaHooks.onError(t3);
                throw new UnsubscribeFailedException(t3.getMessage(), t3);
            }
        }
    }
    
    @Override
    public void onError(final Throwable t) {
        Exceptions.throwIfFatal(t);
        if (!this.done) {
            this.done = true;
            this._onError(t);
        }
    }
    
    @Override
    public void onNext(final T t) {
        try {
            if (!this.done) {
                this.actual.onNext((Object)t);
            }
        }
        catch (Throwable t2) {
            Exceptions.throwOrReport(t2, this);
        }
    }
}
