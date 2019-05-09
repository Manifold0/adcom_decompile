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
import rx.functions.Func2;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorMapPair<T, U, R> implements Operator<Observable<? extends R>, T>
{
    final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
    final Func2<? super T, ? super U, ? extends R> resultSelector;
    
    public OperatorMapPair(final Func1<? super T, ? extends Observable<? extends U>> collectionSelector, final Func2<? super T, ? super U, ? extends R> resultSelector) {
        this.collectionSelector = collectionSelector;
        this.resultSelector = resultSelector;
    }
    
    public static <T, U> Func1<T, Observable<U>> convertSelector(final Func1<? super T, ? extends Iterable<? extends U>> func1) {
        return new Func1<T, Observable<U>>() {
            @Override
            public Observable<U> call(final T t) {
                return Observable.from((Iterable<? extends U>)func1.call(t));
            }
        };
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Observable<? extends R>> subscriber) {
        final MapPairSubscriber mapPairSubscriber = new MapPairSubscriber(subscriber, this.collectionSelector, this.resultSelector);
        subscriber.add(mapPairSubscriber);
        return (Subscriber<? super T>)mapPairSubscriber;
    }
    
    static final class MapPairSubscriber<T, U, R> extends Subscriber<T>
    {
        final Subscriber<? super Observable<? extends R>> actual;
        final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
        boolean done;
        final Func2<? super T, ? super U, ? extends R> resultSelector;
        
        public MapPairSubscriber(final Subscriber<? super Observable<? extends R>> actual, final Func1<? super T, ? extends Observable<? extends U>> collectionSelector, final Func2<? super T, ? super U, ? extends R> resultSelector) {
            this.actual = actual;
            this.collectionSelector = collectionSelector;
            this.resultSelector = resultSelector;
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
                this.actual.onNext(((Observable)this.collectionSelector.call((Object)t)).map(new OuterInnerMapper(t, this.resultSelector)));
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
    
    static final class OuterInnerMapper<T, U, R> implements Func1<U, R>
    {
        final T outer;
        final Func2<? super T, ? super U, ? extends R> resultSelector;
        
        public OuterInnerMapper(final T outer, final Func2<? super T, ? super U, ? extends R> resultSelector) {
            this.outer = outer;
            this.resultSelector = resultSelector;
        }
        
        @Override
        public R call(final U u) {
            return (R)this.resultSelector.call((Object)this.outer, (Object)u);
        }
    }
}
