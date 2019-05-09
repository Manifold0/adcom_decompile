// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.exceptions.CompositeException;
import rx.plugins.RxJavaPlugins;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import retrofit2.Response;
import rx.Observable$OnSubscribe;

final class ResultOnSubscribe<T> implements Observable$OnSubscribe<Result<T>>
{
    private final Observable$OnSubscribe<Response<T>> upstream;
    
    ResultOnSubscribe(final Observable$OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }
    
    public void call(final Subscriber<? super Result<T>> subscriber) {
        this.upstream.call((Object)new ResultSubscriber((rx.Subscriber<? super Result<Object>>)subscriber));
    }
    
    private static class ResultSubscriber<R> extends Subscriber<Response<R>>
    {
        private final Subscriber<? super Result<R>> subscriber;
        
        ResultSubscriber(final Subscriber<? super Result<R>> subscriber) {
            super((Subscriber)subscriber);
            this.subscriber = subscriber;
        }
        
        public void onCompleted() {
            this.subscriber.onCompleted();
        }
        
        public void onError(final Throwable t) {
            try {
                this.subscriber.onNext((Object)Result.error(t));
                this.subscriber.onCompleted();
            }
            catch (Throwable t) {
                try {
                    this.subscriber.onError(t);
                }
                catch (Throwable t2) {
                    Exceptions.throwIfFatal(t2);
                    RxJavaPlugins.getInstance().getErrorHandler().handleError((Throwable)new CompositeException(new Throwable[] { t, t2 }));
                }
            }
        }
        
        public void onNext(final Response<R> response) {
            this.subscriber.onNext((Object)Result.response((retrofit2.Response<Object>)response));
        }
    }
}
