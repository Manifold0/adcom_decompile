// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.OnErrorThrowable;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.Subscription;
import rx.SingleSubscriber;
import rx.functions.Func1;
import rx.Single;

public final class SingleOnSubscribeMap<T, R> implements OnSubscribe<R>
{
    final Single<T> source;
    final Func1<? super T, ? extends R> transformer;
    
    public SingleOnSubscribeMap(final Single<T> source, final Func1<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }
    
    @Override
    public void call(final SingleSubscriber<? super R> singleSubscriber) {
        final MapSubscriber mapSubscriber = new MapSubscriber(singleSubscriber, this.transformer);
        singleSubscriber.add(mapSubscriber);
        this.source.subscribe((SingleSubscriber<? super T>)mapSubscriber);
    }
    
    static final class MapSubscriber<T, R> extends SingleSubscriber<T>
    {
        final SingleSubscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;
        
        public MapSubscriber(final SingleSubscriber<? super R> actual, final Func1<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
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
        public void onSuccess(final T t) {
            try {
                this.actual.onSuccess((Object)this.mapper.call((Object)t));
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.onError(OnErrorThrowable.addValueAsLastCause(t2, t));
            }
        }
    }
}
