// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.util.List;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.operators.BackpressureUtils;
import rx.exceptions.MissingBackpressureException;
import rx.Subscriber;
import rx.Observer;
import rx.Subscription;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;

public final class PublishSubject<T> extends Subject<T, T>
{
    final PublishSubjectState<T> state;
    
    protected PublishSubject(final PublishSubjectState<T> state) {
        super(state);
        this.state = state;
    }
    
    public static <T> PublishSubject<T> create() {
        return new PublishSubject<T>(new PublishSubjectState<T>());
    }
    
    public Throwable getThrowable() {
        if (this.state.get() == PublishSubjectState.TERMINATED) {
            return this.state.error;
        }
        return null;
    }
    
    public boolean hasCompleted() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error == null;
    }
    
    @Override
    public boolean hasObservers() {
        return ((PublishSubjectProducer[])this.state.get()).length != 0;
    }
    
    public boolean hasThrowable() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error != null;
    }
    
    @Override
    public void onCompleted() {
        this.state.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.state.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.state.onNext(t);
    }
    
    static final class PublishSubjectProducer<T> extends AtomicLong implements Producer, Subscription, Observer<T>
    {
        private static final long serialVersionUID = 6451806817170721536L;
        final Subscriber<? super T> actual;
        final PublishSubjectState<T> parent;
        long produced;
        
        public PublishSubjectProducer(final PublishSubjectState<T> parent, final Subscriber<? super T> actual) {
            this.parent = parent;
            this.actual = actual;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() == Long.MIN_VALUE;
        }
        
        @Override
        public void onCompleted() {
            if (this.get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.get() != Long.MIN_VALUE) {
                this.actual.onError(t);
            }
        }
        
        @Override
        public void onNext(final T t) {
            final long value = this.get();
            if (value != Long.MIN_VALUE) {
                final long produced = this.produced;
                if (value == produced) {
                    this.unsubscribe();
                    this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
                    return;
                }
                this.produced = 1L + produced;
                this.actual.onNext((Object)t);
            }
        }
        
        @Override
        public void request(final long n) {
            if (BackpressureUtils.validate(n)) {
                long value;
                do {
                    value = this.get();
                    if (value == Long.MIN_VALUE) {
                        return;
                    }
                } while (!this.compareAndSet(value, BackpressureUtils.addCap(value, n)));
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }
    }
    
    static final class PublishSubjectState<T> extends AtomicReference<PublishSubjectProducer<T>[]> implements OnSubscribe<T>, Observer<T>
    {
        static final PublishSubjectProducer[] EMPTY;
        static final PublishSubjectProducer[] TERMINATED;
        private static final long serialVersionUID = -7568940796666027140L;
        Throwable error;
        
        static {
            EMPTY = new PublishSubjectProducer[0];
            TERMINATED = new PublishSubjectProducer[0];
        }
        
        public PublishSubjectState() {
            this.lazySet(PublishSubjectState.EMPTY);
        }
        
        boolean add(final PublishSubjectProducer<T> publishSubjectProducer) {
            PublishSubjectProducer[] array;
            PublishSubjectProducer[] array2;
            do {
                array = (PublishSubjectProducer[])this.get();
                if (array == PublishSubjectState.TERMINATED) {
                    return false;
                }
                final int length = array.length;
                array2 = new PublishSubjectProducer[length + 1];
                System.arraycopy(array, 0, array2, 0, length);
                array2[length] = publishSubjectProducer;
            } while (!this.compareAndSet(array, array2));
            return true;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            final PublishSubjectProducer producer = new PublishSubjectProducer((PublishSubjectState)this, subscriber);
            subscriber.add(producer);
            subscriber.setProducer(producer);
            if (this.add((PublishSubjectProducer<T>)producer)) {
                if (producer.isUnsubscribed()) {
                    this.remove((PublishSubjectProducer<T>)producer);
                }
                return;
            }
            final Throwable error = this.error;
            if (error != null) {
                subscriber.onError(error);
                return;
            }
            subscriber.onCompleted();
        }
        
        @Override
        public void onCompleted() {
            final PublishSubjectProducer[] array = this.getAndSet(PublishSubjectState.TERMINATED);
            for (int length = array.length, i = 0; i < length; ++i) {
                array[i].onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            List<Throwable> list = null;
            final PublishSubjectProducer[] array = this.getAndSet(PublishSubjectState.TERMINATED);
            final int length = array.length;
            int i = 0;
        Label_0043_Outer:
            while (i < length) {
                final PublishSubjectProducer publishSubjectProducer = array[i];
                while (true) {
                    try {
                        publishSubjectProducer.onError(error);
                        ++i;
                        continue Label_0043_Outer;
                    }
                    catch (Throwable t) {
                        List<Throwable> list2 = list;
                        if (list == null) {
                            list2 = new ArrayList<Throwable>(1);
                        }
                        list2.add(t);
                        list = list2;
                        continue;
                    }
                    break;
                }
                break;
            }
            Exceptions.throwIfAny(list);
        }
        
        @Override
        public void onNext(final T t) {
            final PublishSubjectProducer[] array = this.get();
            for (int length = array.length, i = 0; i < length; ++i) {
                array[i].onNext(t);
            }
        }
        
        void remove(final PublishSubjectProducer<T> publishSubjectProducer) {
            while (true) {
                final PublishSubjectProducer[] array = this.get();
                if (array == PublishSubjectState.TERMINATED || array == PublishSubjectState.EMPTY) {
                    break;
                }
                final int length = array.length;
                final int n = -1;
                int n2 = 0;
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= length) {
                        break;
                    }
                    if (array[n2] == publishSubjectProducer) {
                        n3 = n2;
                        break;
                    }
                    ++n2;
                }
                if (n3 < 0) {
                    break;
                }
                PublishSubjectProducer[] empty;
                if (length == 1) {
                    empty = PublishSubjectState.EMPTY;
                }
                else {
                    empty = new PublishSubjectProducer[length - 1];
                    System.arraycopy(array, 0, empty, 0, n3);
                    System.arraycopy(array, n3 + 1, empty, n3, length - n3 - 1);
                }
                if (this.compareAndSet(array, empty)) {
                    return;
                }
            }
        }
    }
}
