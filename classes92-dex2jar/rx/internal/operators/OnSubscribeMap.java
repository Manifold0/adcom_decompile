// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeMap<T, R> implements OnSubscribe<R>
{
    final Observable<T> source;
    final Func1<? super T, ? extends R> transformer;
    
    public OnSubscribeMap(final Observable<T> source, final Func1<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        final MapSubscriber mapSubscriber = new MapSubscriber(subscriber, this.transformer);
        subscriber.add(mapSubscriber);
        this.source.unsafeSubscribe((Subscriber<? super T>)mapSubscriber);
    }
    
    static final class MapSubscriber<T, R> extends Subscriber<T>
    {
        final Subscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;
        
        public MapSubscriber(final Subscriber<? super R> actual, final Func1<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            try {
                this.actual.onNext((Object)this.mapper.call((Object)t));
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.onError(OnErrorThrowable.addValueAsLastCause(t2, t));
            }
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.actual.setProducer(producer);
        }
    }
}
