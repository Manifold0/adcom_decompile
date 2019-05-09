// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.Subscription;
import rx.SingleSubscriber;
import rx.functions.Action1;
import rx.Single;

public final class SingleDoOnEvent<T> implements OnSubscribe<T>
{
    final Action1<Throwable> onError;
    final Action1<? super T> onSuccess;
    final Single<T> source;
    
    public SingleDoOnEvent(final Single<T> source, final Action1<? super T> onSuccess, final Action1<Throwable> onError) {
        this.source = source;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleDoOnEventSubscriber<Object> singleDoOnEventSubscriber = new SingleDoOnEventSubscriber<Object>((SingleSubscriber<? super Object>)singleSubscriber, (Action1<? super Object>)this.onSuccess, this.onError);
        singleSubscriber.add(singleDoOnEventSubscriber);
        this.source.subscribe(singleDoOnEventSubscriber);
    }
    
    static final class SingleDoOnEventSubscriber<T> extends SingleSubscriber<T>
    {
        final SingleSubscriber<? super T> actual;
        final Action1<Throwable> onError;
        final Action1<? super T> onSuccess;
        
        SingleDoOnEventSubscriber(final SingleSubscriber<? super T> actual, final Action1<? super T> onSuccess, final Action1<Throwable> onError) {
            this.actual = actual;
            this.onSuccess = onSuccess;
            this.onError = onError;
        }
        
        @Override
        public void onError(final Throwable t) {
            try {
                this.onError.call(t);
                this.actual.onError(t);
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.actual.onError(new CompositeException(new Throwable[] { t, t2 }));
            }
        }
        
        @Override
        public void onSuccess(final T t) {
            try {
                this.onSuccess.call((Object)t);
                this.actual.onSuccess((Object)t);
            }
            catch (Throwable t2) {
                Exceptions.throwOrReport(t2, this, t);
            }
        }
    }
}
