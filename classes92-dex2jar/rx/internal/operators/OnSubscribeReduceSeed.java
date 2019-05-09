// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Func2;
import rx.Observable;

public final class OnSubscribeReduceSeed<T, R> implements OnSubscribe<R>
{
    final R initialValue;
    final Func2<R, ? super T, R> reducer;
    final Observable<T> source;
    
    public OnSubscribeReduceSeed(final Observable<T> source, final R initialValue, final Func2<R, ? super T, R> reducer) {
        this.source = source;
        this.initialValue = initialValue;
        this.reducer = reducer;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        new ReduceSeedSubscriber<Object, Object>((Subscriber<? super Object>)subscriber, this.initialValue, (Func2<Object, ? super Object, Object>)this.reducer).subscribeTo(this.source);
    }
    
    static final class ReduceSeedSubscriber<T, R> extends DeferredScalarSubscriber<T, R>
    {
        final Func2<R, ? super T, R> reducer;
        
        public ReduceSeedSubscriber(final Subscriber<? super R> subscriber, final R value, final Func2<R, ? super T, R> reducer) {
            super(subscriber);
            this.value = value;
            this.hasValue = true;
            this.reducer = reducer;
        }
        
        @Override
        public void onNext(final T t) {
            try {
                this.value = this.reducer.call(this.value, (Object)t);
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.actual.onError(t2);
            }
        }
    }
}
