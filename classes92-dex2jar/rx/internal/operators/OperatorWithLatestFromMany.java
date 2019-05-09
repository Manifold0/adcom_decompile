// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.Iterator;
import rx.Subscription;
import java.util.Arrays;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.FuncN;
import rx.Observable;

public final class OperatorWithLatestFromMany<T, R> implements OnSubscribe<R>
{
    final FuncN<R> combiner;
    final Observable<T> main;
    final Observable<?>[] others;
    final Iterable<Observable<?>> othersIterable;
    
    public OperatorWithLatestFromMany(final Observable<T> main, final Observable<?>[] others, final Iterable<Observable<?>> othersIterable, final FuncN<R> combiner) {
        this.main = main;
        this.others = others;
        this.othersIterable = othersIterable;
        this.combiner = combiner;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        int n = 0;
        Observable<?>[] others;
        int length;
        if (this.others != null) {
            others = this.others;
            length = others.length;
        }
        else {
            Observable<?>[] array = (Observable<?>[])new Observable[8];
            final Iterator<Observable<?>> iterator = this.othersIterable.iterator();
            while (true) {
                length = n;
                others = array;
                if (!iterator.hasNext()) {
                    break;
                }
                final Observable<?> observable = iterator.next();
                Observable<?>[] array2 = array;
                if (n == array.length) {
                    array2 = Arrays.copyOf(array, (n >> 2) + n);
                }
                array2[n] = observable;
                ++n;
                array = array2;
            }
        }
        final WithLatestMainSubscriber withLatestMainSubscriber = new WithLatestMainSubscriber<Object, Object>((Subscriber<? super Object>)subscriber, (FuncN<Object>)this.combiner, length);
        serializedSubscriber.add(withLatestMainSubscriber);
        for (int i = 0; i < length; ++i) {
            if (serializedSubscriber.isUnsubscribed()) {
                return;
            }
            final WithLatestOtherSubscriber withLatestOtherSubscriber = new WithLatestOtherSubscriber((WithLatestMainSubscriber<?, ?>)withLatestMainSubscriber, i + 1);
            withLatestMainSubscriber.add(withLatestOtherSubscriber);
            others[i].unsafeSubscribe(withLatestOtherSubscriber);
        }
        this.main.unsafeSubscribe((Subscriber<? super T>)withLatestMainSubscriber);
    }
    
    static final class WithLatestMainSubscriber<T, R> extends Subscriber<T>
    {
        static final Object EMPTY;
        final Subscriber<? super R> actual;
        final FuncN<R> combiner;
        final AtomicReferenceArray<Object> current;
        boolean done;
        final AtomicInteger ready;
        
        static {
            EMPTY = new Object();
        }
        
        public WithLatestMainSubscriber(final Subscriber<? super R> actual, final FuncN<R> combiner, final int n) {
            this.actual = actual;
            this.combiner = combiner;
            final AtomicReferenceArray<Object> current = new AtomicReferenceArray<Object>(n + 1);
            for (int i = 0; i <= n; ++i) {
                current.lazySet(i, WithLatestMainSubscriber.EMPTY);
            }
            this.current = current;
            this.ready = new AtomicInteger(n);
            this.request(0L);
        }
        
        void innerComplete(final int n) {
            if (this.current.get(n) == WithLatestMainSubscriber.EMPTY) {
                this.onCompleted();
            }
        }
        
        void innerError(final int n, final Throwable t) {
            this.onError(t);
        }
        
        void innerNext(final int n, final Object o) {
            if (this.current.getAndSet(n, o) == WithLatestMainSubscriber.EMPTY) {
                this.ready.decrementAndGet();
            }
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.unsubscribe();
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.done = true;
            this.unsubscribe();
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            if (this.ready.get() == 0) {
                final AtomicReferenceArray<Object> current = this.current;
                final int length = current.length();
                current.lazySet(0, t);
                final Object[] array = new Object[current.length()];
                for (int i = 0; i < length; ++i) {
                    array[i] = current.get(i);
                }
                try {
                    this.actual.onNext((Object)this.combiner.call(array));
                    return;
                }
                catch (Throwable t2) {
                    Exceptions.throwIfFatal(t2);
                    this.onError(t2);
                    return;
                }
            }
            this.request(1L);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            super.setProducer(producer);
            this.actual.setProducer(producer);
        }
    }
    
    static final class WithLatestOtherSubscriber extends Subscriber<Object>
    {
        final int index;
        final WithLatestMainSubscriber<?, ?> parent;
        
        public WithLatestOtherSubscriber(final WithLatestMainSubscriber<?, ?> parent, final int index) {
            this.parent = parent;
            this.index = index;
        }
        
        @Override
        public void onCompleted() {
            this.parent.innerComplete(this.index);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.parent.innerError(this.index, t);
        }
        
        @Override
        public void onNext(final Object o) {
            this.parent.innerNext(this.index, o);
        }
    }
}
