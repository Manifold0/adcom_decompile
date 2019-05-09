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
import rx.Observable;

public class OperatorCast<T, R> implements Operator<R, T>
{
    final Class<R> castClass;
    
    public OperatorCast(final Class<R> castClass) {
        this.castClass = castClass;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final CastSubscriber castSubscriber = new CastSubscriber(subscriber, this.castClass);
        subscriber.add(castSubscriber);
        return (Subscriber<? super T>)castSubscriber;
    }
    
    static final class CastSubscriber<T, R> extends Subscriber<T>
    {
        final Subscriber<? super R> actual;
        final Class<R> castClass;
        boolean done;
        
        public CastSubscriber(final Subscriber<? super R> actual, final Class<R> castClass) {
            this.actual = actual;
            this.castClass = castClass;
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
                this.actual.onNext((Object)this.castClass.cast(t));
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
