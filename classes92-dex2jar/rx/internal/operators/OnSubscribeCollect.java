// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.Observable;

public final class OnSubscribeCollect<T, R> implements OnSubscribe<R>
{
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;
    
    public OnSubscribeCollect(final Observable<T> source, final Func0<R> collectionFactory, final Action2<R, ? super T> collector) {
        this.source = source;
        this.collectionFactory = collectionFactory;
        this.collector = collector;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        try {
            new CollectSubscriber<Object, Object>((Subscriber<? super Object>)subscriber, this.collectionFactory.call(), (Action2<Object, ? super Object>)this.collector).subscribeTo(this.source);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            subscriber.onError(t);
        }
    }
    
    static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R>
    {
        final Action2<R, ? super T> collector;
        
        public CollectSubscriber(final Subscriber<? super R> subscriber, final R value, final Action2<R, ? super T> collector) {
            super(subscriber);
            this.value = value;
            this.hasValue = true;
            this.collector = collector;
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            try {
                this.collector.call(this.value, (Object)t);
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.onError(t2);
            }
        }
    }
}
