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

public final class OnSubscribeFilter<T> implements OnSubscribe<T>
{
    final Func1<? super T, Boolean> predicate;
    final Observable<T> source;
    
    public OnSubscribeFilter(final Observable<T> source, final Func1<? super T, Boolean> predicate) {
        this.source = source;
        this.predicate = predicate;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final FilterSubscriber<Object> filterSubscriber = new FilterSubscriber<Object>((Subscriber<? super Object>)subscriber, (Func1<? super Object, Boolean>)this.predicate);
        subscriber.add(filterSubscriber);
        this.source.unsafeSubscribe(filterSubscriber);
    }
    
    static final class FilterSubscriber<T> extends Subscriber<T>
    {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;
        
        public FilterSubscriber(final Subscriber<? super T> actual, final Func1<? super T, Boolean> predicate) {
            this.actual = actual;
            this.predicate = predicate;
            this.request(0L);
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
                if (this.predicate.call((Object)t)) {
                    this.actual.onNext((Object)t);
                    return;
                }
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.onError(OnErrorThrowable.addValueAsLastCause(t2, t));
                return;
            }
            this.request(1L);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            super.setProducer(producer);
            this.actual.setProducer(producer);
        }
    }
}
