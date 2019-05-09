// 
// Decompiled by Procyon v0.5.34
// 

package rx.observables;

import java.util.Iterator;
import java.util.ArrayList;
import rx.internal.operators.BufferUntilSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
import rx.observers.SerializedObserver;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.functions.Func1;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action2;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.Observer;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.annotations.Experimental;
import rx.Observable;

@Experimental
public abstract class AsyncOnSubscribe<S, T> implements OnSubscribe<T>
{
    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(final Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3) {
        return new AsyncOnSubscribeImpl<S, T>(func0, (Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S>)new Func3<S, Long, Observer<Observable<? extends T>>, S>() {
            @Override
            public S call(final S n, final Long n2, final Observer<Observable<? extends T>> observer) {
                action3.call(n, n2, observer);
                return n;
            }
        });
    }
    
    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(final Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3, final Action1<? super S> action4) {
        return new AsyncOnSubscribeImpl<S, T>(func0, (Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S>)new Func3<S, Long, Observer<Observable<? extends T>>, S>() {
            @Override
            public S call(final S n, final Long n2, final Observer<Observable<? extends T>> observer) {
                action3.call(n, n2, observer);
                return n;
            }
        }, action4);
    }
    
    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createStateful(final Func0<? extends S> func0, final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func2) {
        return new AsyncOnSubscribeImpl<S, T>(func0, func2);
    }
    
    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createStateful(final Func0<? extends S> func0, final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func2, final Action1<? super S> action1) {
        return new AsyncOnSubscribeImpl<S, T>(func0, func2, action1);
    }
    
    @Experimental
    public static <T> AsyncOnSubscribe<Void, T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2) {
        return new AsyncOnSubscribeImpl<Void, T>(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() {
            @Override
            public Void call(final Void void1, final Long n, final Observer<Observable<? extends T>> observer) {
                action2.call(n, observer);
                return void1;
            }
        });
    }
    
    @Experimental
    public static <T> AsyncOnSubscribe<Void, T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2, final Action0 action3) {
        return new AsyncOnSubscribeImpl<Void, T>(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() {
            @Override
            public Void call(final Void void1, final Long n, final Observer<Observable<? extends T>> observer) {
                action2.call(n, observer);
                return null;
            }
        }, new Action1<Void>() {
            @Override
            public void call(final Void void1) {
                action3.call();
            }
        });
    }
    
    @Override
    public final void call(final Subscriber<? super T> subscriber) {
        try {
            final Object generateState = this.generateState();
            final UnicastSubject<Observable<Object>> create = UnicastSubject.create();
            final AsyncOuterManager producer = new AsyncOuterManager((AsyncOnSubscribe<Object, Object>)this, generateState, create);
            final Subscriber<T> subscriber2 = new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }
                
                @Override
                public void onError(final Throwable t) {
                    subscriber.onError(t);
                }
                
                @Override
                public void onNext(final T t) {
                    subscriber.onNext(t);
                }
                
                @Override
                public void setProducer(final Producer concatProducer) {
                    producer.setConcatProducer(concatProducer);
                }
            };
            create.onBackpressureBuffer().concatMap((Func1<? super Object, ? extends Observable<?>>)new Func1<Observable<T>, Observable<T>>() {
                @Override
                public Observable<T> call(final Observable<T> observable) {
                    return observable.onBackpressureBuffer();
                }
            }).unsafeSubscribe((Subscriber<? super Object>)subscriber2);
            subscriber.add(subscriber2);
            subscriber.add(producer);
            subscriber.setProducer(producer);
        }
        catch (Throwable t) {
            subscriber.onError(t);
        }
    }
    
    protected abstract S generateState();
    
    protected abstract S next(final S p0, final long p1, final Observer<Observable<? extends T>> p2);
    
    protected void onUnsubscribe(final S n) {
    }
    
    static final class AsyncOnSubscribeImpl<S, T> extends AsyncOnSubscribe<S, T>
    {
        private final Func0<? extends S> generator;
        private final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;
        
        public AsyncOnSubscribeImpl(final Func0<? extends S> func0, final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func2) {
            this(func0, func2, null);
        }
        
        AsyncOnSubscribeImpl(final Func0<? extends S> generator, final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next, final Action1<? super S> onUnsubscribe) {
            this.generator = generator;
            this.next = next;
            this.onUnsubscribe = onUnsubscribe;
        }
        
        public AsyncOnSubscribeImpl(final Func3<S, Long, Observer<Observable<? extends T>>, S> func3) {
            this(null, (Func3)func3, null);
        }
        
        public AsyncOnSubscribeImpl(final Func3<S, Long, Observer<Observable<? extends T>>, S> func3, final Action1<? super S> action1) {
            this(null, (Func3)func3, action1);
        }
        
        @Override
        protected S generateState() {
            if (this.generator == null) {
                return null;
            }
            return (S)this.generator.call();
        }
        
        @Override
        protected S next(final S n, final long n2, final Observer<Observable<? extends T>> observer) {
            return (S)this.next.call((Object)n, Long.valueOf(n2), observer);
        }
        
        @Override
        protected void onUnsubscribe(final S n) {
            if (this.onUnsubscribe != null) {
                this.onUnsubscribe.call((Object)n);
            }
        }
    }
    
    static final class AsyncOuterManager<S, T> implements Producer, Subscription, Observer<Observable<? extends T>>
    {
        Producer concatProducer;
        boolean emitting;
        long expectedDelivery;
        private boolean hasTerminated;
        final AtomicBoolean isUnsubscribed;
        private final UnicastSubject<Observable<T>> merger;
        private boolean onNextCalled;
        private final AsyncOnSubscribe<S, T> parent;
        List<Long> requests;
        private final SerializedObserver<Observable<? extends T>> serializedSubscriber;
        private S state;
        final CompositeSubscription subscriptions;
        
        public AsyncOuterManager(final AsyncOnSubscribe<S, T> parent, final S state, final UnicastSubject<Observable<T>> merger) {
            this.subscriptions = new CompositeSubscription();
            this.parent = parent;
            this.serializedSubscriber = new SerializedObserver<Observable<? extends T>>(this);
            this.state = state;
            this.merger = merger;
            this.isUnsubscribed = new AtomicBoolean();
        }
        
        private void handleThrownError(final Throwable t) {
            if (this.hasTerminated) {
                RxJavaHooks.onError(t);
                return;
            }
            this.hasTerminated = true;
            this.merger.onError(t);
            this.cleanup();
        }
        
        private void subscribeBufferToObservable(final Observable<? extends T> observable) {
            final BufferUntilSubscriber<Object> create = BufferUntilSubscriber.create();
            final Subscriber<T> subscriber = new Subscriber<T>() {
                long remaining = this.val$expected;
                final /* synthetic */ long val$expected = AsyncOuterManager.this.expectedDelivery;
                
                @Override
                public void onCompleted() {
                    create.onCompleted();
                    final long remaining = this.remaining;
                    if (remaining > 0L) {
                        AsyncOuterManager.this.requestRemaining(remaining);
                    }
                }
                
                @Override
                public void onError(final Throwable t) {
                    create.onError(t);
                }
                
                @Override
                public void onNext(final T t) {
                    --this.remaining;
                    create.onNext(t);
                }
            };
            this.subscriptions.add(subscriber);
            observable.doOnTerminate(new Action0() {
                @Override
                public void call() {
                    AsyncOuterManager.this.subscriptions.remove(subscriber);
                }
            }).subscribe(subscriber);
            this.merger.onNext((Observable<T>)create);
        }
        
        void cleanup() {
            this.subscriptions.unsubscribe();
            try {
                this.parent.onUnsubscribe(this.state);
            }
            catch (Throwable t) {
                this.handleThrownError(t);
            }
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.isUnsubscribed.get();
        }
        
        public void nextIteration(final long n) {
            this.state = this.parent.next(this.state, n, this.serializedSubscriber);
        }
        
        @Override
        public void onCompleted() {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            this.merger.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            this.merger.onError(t);
        }
        
        @Override
        public void onNext(final Observable<? extends T> observable) {
            if (this.onNextCalled) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.onNextCalled = true;
            if (this.hasTerminated) {
                return;
            }
            this.subscribeBufferToObservable(observable);
        }
        
        @Override
        public void request(final long n) {
            if (n != 0L) {
                if (n < 0L) {
                    throw new IllegalStateException("Request can't be negative! " + n);
                }
            Label_0119_Outer:
                while (true) {
                    boolean b = false;
                    while (true) {
                        Label_0162: {
                            synchronized (this) {
                                // monitorexit(this)
                                while (true) {
                                    Label_0147: {
                                        if (!this.emitting) {
                                            break Label_0147;
                                        }
                                        List<Long> requests;
                                        if ((requests = this.requests) == null) {
                                            requests = new ArrayList<Long>();
                                            this.requests = requests;
                                        }
                                        requests.add(n);
                                        b = true;
                                        this.concatProducer.request(n);
                                        if (b || this.tryEmit(n)) {
                                            return;
                                        }
                                        synchronized (this) {
                                            if (this.requests == null) {
                                                this.emitting = false;
                                                return;
                                            }
                                            break Label_0162;
                                        }
                                    }
                                    this.emitting = true;
                                    continue Label_0119_Outer;
                                }
                            }
                        }
                        this.requests = null;
                        final List<Long> list;
                        final Iterator<Long> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            if (this.tryEmit(iterator.next())) {
                                break Label_0119_Outer;
                            }
                        }
                        continue;
                    }
                }
                // monitorexit(this)
            }
        }
        
        public void requestRemaining(final long n) {
            if (n != 0L) {
                if (n < 0L) {
                    throw new IllegalStateException("Request can't be negative! " + n);
                }
                synchronized (this) {
                    if (this.emitting) {
                        List<Long> requests;
                        if ((requests = this.requests) == null) {
                            requests = new ArrayList<Long>();
                            this.requests = requests;
                        }
                        requests.add(n);
                        return;
                    }
                }
                this.emitting = true;
                // monitorexit(this)
                if (!this.tryEmit(n)) {
                Block_5:
                    while (true) {
                        synchronized (this) {
                            if (this.requests == null) {
                                this.emitting = false;
                                return;
                            }
                        }
                        this.requests = null;
                        // monitorexit(this)
                        final List<Long> list;
                        final Iterator<Long> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            if (this.tryEmit(iterator.next())) {
                                break Block_5;
                            }
                        }
                    }
                }
            }
        }
        
        void setConcatProducer(final Producer concatProducer) {
            if (this.concatProducer != null) {
                throw new IllegalStateException("setConcatProducer may be called at most once!");
            }
            this.concatProducer = concatProducer;
        }
        
        boolean tryEmit(final long expectedDelivery) {
            if (this.isUnsubscribed()) {
                this.cleanup();
                return true;
            }
            try {
                this.onNextCalled = false;
                this.nextIteration(this.expectedDelivery = expectedDelivery);
                if (this.hasTerminated || this.isUnsubscribed()) {
                    this.cleanup();
                    return true;
                }
            }
            catch (Throwable t) {
                this.handleThrownError(t);
                return true;
            }
            if (!this.onNextCalled) {
                this.handleThrownError(new IllegalStateException("No events emitted!"));
                return true;
            }
            return false;
        }
        
        @Override
        public void unsubscribe() {
            if (this.isUnsubscribed.compareAndSet(false, true)) {
                synchronized (this) {
                    if (this.emitting) {
                        (this.requests = new ArrayList<Long>()).add(0L);
                        return;
                    }
                    this.emitting = true;
                    // monitorexit(this)
                    this.cleanup();
                }
            }
        }
    }
    
    static final class UnicastSubject<T> extends Observable<T> implements Observer<T>
    {
        private final State<T> state;
        
        protected UnicastSubject(final State<T> state) {
            super((OnSubscribe)state);
            this.state = state;
        }
        
        public static <T> UnicastSubject<T> create() {
            return new UnicastSubject<T>(new State<T>());
        }
        
        @Override
        public void onCompleted() {
            this.state.subscriber.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.state.subscriber.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.state.subscriber.onNext((Object)t);
        }
        
        static final class State<T> implements OnSubscribe<T>
        {
            Subscriber<? super T> subscriber;
            
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                synchronized (this) {
                    if (this.subscriber == null) {
                        this.subscriber = subscriber;
                        return;
                    }
                    // monitorexit(this)
                    subscriber.onError(new IllegalStateException("There can be only one subscriber"));
                }
            }
        }
    }
}
