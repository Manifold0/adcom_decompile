// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaPlugins;
import rx.Subscriber;
import retrofit2.Response;
import rx.Observable$OnSubscribe;

final class BodyOnSubscribe<T> implements Observable$OnSubscribe<T>
{
    private final Observable$OnSubscribe<Response<T>> upstream;
    
    BodyOnSubscribe(final Observable$OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }
    
    public void call(final Subscriber<? super T> subscriber) {
        this.upstream.call((Object)new BodySubscriber((rx.Subscriber<? super Object>)subscriber));
    }
    
    private static class BodySubscriber<R> extends Subscriber<Response<R>>
    {
        private final Subscriber<? super R> subscriber;
        private boolean subscriberTerminated;
        
        BodySubscriber(final Subscriber<? super R> subscriber) {
            super((Subscriber)subscriber);
            this.subscriber = subscriber;
        }
        
        public void onCompleted() {
            if (!this.subscriberTerminated) {
                this.subscriber.onCompleted();
            }
        }
        
        public void onError(final Throwable t) {
            if (!this.subscriberTerminated) {
                this.subscriber.onError(t);
                return;
            }
            final AssertionError assertionError = new AssertionError((Object)"This should never happen! Report as a Retrofit bug with the full stacktrace.");
            assertionError.initCause(t);
            RxJavaPlugins.getInstance().getErrorHandler().handleError((Throwable)assertionError);
        }
        
        public void onNext(Response<R> ex) {
            if (((Response)ex).isSuccessful()) {
                this.subscriber.onNext(((Response)ex).body());
                return;
            }
            this.subscriberTerminated = true;
            ex = new HttpException((Response<?>)ex);
            try {
                this.subscriber.onError((Throwable)ex);
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaPlugins.getInstance().getErrorHandler().handleError((Throwable)new CompositeException(new Throwable[] { (Throwable)ex, t }));
            }
        }
    }
}
