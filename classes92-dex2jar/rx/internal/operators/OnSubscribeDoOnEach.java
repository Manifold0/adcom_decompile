// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.Arrays;
import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.Observer;
import rx.Observable;

public class OnSubscribeDoOnEach<T> implements OnSubscribe<T>
{
    private final Observer<? super T> doOnEachObserver;
    private final Observable<T> source;
    
    public OnSubscribeDoOnEach(final Observable<T> source, final Observer<? super T> doOnEachObserver) {
        this.source = source;
        this.doOnEachObserver = doOnEachObserver;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(new DoOnEachSubscriber<Object>(subscriber, this.doOnEachObserver));
    }
    
    private static final class DoOnEachSubscriber<T> extends Subscriber<T>
    {
        private final Observer<? super T> doOnEachObserver;
        private boolean done;
        private final Subscriber<? super T> subscriber;
        
        DoOnEachSubscriber(final Subscriber<? super T> subscriber, final Observer<? super T> doOnEachObserver) {
            super(subscriber);
            this.subscriber = subscriber;
            this.doOnEachObserver = doOnEachObserver;
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            try {
                this.doOnEachObserver.onCompleted();
                this.done = true;
                this.subscriber.onCompleted();
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, this);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.done = true;
            try {
                this.doOnEachObserver.onError(t);
                this.subscriber.onError(t);
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.subscriber.onError(new CompositeException(Arrays.asList(t, t2)));
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            try {
                this.doOnEachObserver.onNext((Object)t);
                this.subscriber.onNext((Object)t);
            }
            catch (Throwable t2) {
                Exceptions.throwOrReport(t2, this, t);
            }
        }
    }
}
