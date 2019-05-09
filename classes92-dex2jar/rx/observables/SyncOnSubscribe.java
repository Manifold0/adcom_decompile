// 
// Decompiled by Procyon v0.5.34
// 

package rx.observables;

import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicLong;
import rx.exceptions.Exceptions;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.annotations.Beta;
import rx.functions.Func2;
import rx.Observer;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.Observable;

public abstract class SyncOnSubscribe<S, T> implements OnSubscribe<T>
{
    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(final Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2) {
        return new SyncOnSubscribeImpl<S, T>(func0, (Func2<? super S, ? super Observer<? super T>, ? extends S>)new Func2<S, Observer<? super T>, S>() {
            @Override
            public S call(final S n, final Observer<? super T> observer) {
                action2.call(n, observer);
                return n;
            }
        });
    }
    
    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(final Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2, final Action1<? super S> action3) {
        return new SyncOnSubscribeImpl<S, T>(func0, (Func2<? super S, ? super Observer<? super T>, ? extends S>)new Func2<S, Observer<? super T>, S>() {
            @Override
            public S call(final S n, final Observer<? super T> observer) {
                action2.call(n, observer);
                return n;
            }
        }, action3);
    }
    
    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(final Func0<? extends S> func0, final Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
        return new SyncOnSubscribeImpl<S, T>(func0, func2);
    }
    
    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(final Func0<? extends S> func0, final Func2<? super S, ? super Observer<? super T>, ? extends S> func2, final Action1<? super S> action1) {
        return new SyncOnSubscribeImpl<S, T>(func0, func2, action1);
    }
    
    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1) {
        return new SyncOnSubscribeImpl<Void, T>(new Func2<Void, Observer<? super T>, Void>() {
            @Override
            public Void call(final Void void1, final Observer<? super T> observer) {
                action1.call(observer);
                return void1;
            }
        });
    }
    
    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1, final Action0 action2) {
        return new SyncOnSubscribeImpl<Void, T>(new Func2<Void, Observer<? super T>, Void>() {
            @Override
            public Void call(final Void void1, final Observer<? super T> observer) {
                action1.call(observer);
                return null;
            }
        }, new Action1<Void>() {
            @Override
            public void call(final Void void1) {
                action2.call();
            }
        });
    }
    
    @Override
    public final void call(final Subscriber<? super T> subscriber) {
        try {
            final SubscriptionProducer producer = new SubscriptionProducer(subscriber, this, this.generateState());
            subscriber.add(producer);
            subscriber.setProducer(producer);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            subscriber.onError(t);
        }
    }
    
    protected abstract S generateState();
    
    protected abstract S next(final S p0, final Observer<? super T> p1);
    
    protected void onUnsubscribe(final S n) {
    }
    
    static final class SubscriptionProducer<S, T> extends AtomicLong implements Producer, Subscription, Observer<T>
    {
        private static final long serialVersionUID = -3736864024352728072L;
        private final Subscriber<? super T> actualSubscriber;
        private boolean hasTerminated;
        private boolean onNextCalled;
        private final SyncOnSubscribe<S, T> parent;
        private S state;
        
        SubscriptionProducer(final Subscriber<? super T> actualSubscriber, final SyncOnSubscribe<S, T> parent, final S state) {
            this.actualSubscriber = actualSubscriber;
            this.parent = parent;
            this.state = state;
        }
        
        private void doUnsubscribe() {
            try {
                this.parent.onUnsubscribe(this.state);
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaHooks.onError(t);
            }
        }
        
        private void fastPath() {
            final SyncOnSubscribe<S, T> parent = this.parent;
            final Subscriber<? super T> actualSubscriber = this.actualSubscriber;
            try {
                do {
                    this.onNextCalled = false;
                    this.nextIteration(parent);
                } while (!this.tryUnsubscribe());
            }
            catch (Throwable t) {
                this.handleThrownError(actualSubscriber, t);
            }
        }
        
        private void handleThrownError(final Subscriber<? super T> subscriber, final Throwable t) {
            if (this.hasTerminated) {
                RxJavaHooks.onError(t);
                return;
            }
            this.hasTerminated = true;
            subscriber.onError(t);
            this.unsubscribe();
        }
        
        private void nextIteration(final SyncOnSubscribe<S, T> syncOnSubscribe) {
            this.state = syncOnSubscribe.next(this.state, this);
        }
        
        private void slowPath(long addAndGet) {
            final SyncOnSubscribe<S, T> parent = this.parent;
            final Subscriber<? super T> actualSubscriber = this.actualSubscriber;
            do {
                long n = addAndGet;
                long n2;
                do {
                    try {
                        this.onNextCalled = false;
                        this.nextIteration(parent);
                        if (this.tryUnsubscribe()) {
                            return;
                        }
                    }
                    catch (Throwable t) {
                        this.handleThrownError(actualSubscriber, t);
                        return;
                    }
                    n2 = n;
                    if (this.onNextCalled) {
                        n2 = n - 1L;
                    }
                    n = n2;
                } while (n2 != 0L);
            } while ((addAndGet = this.addAndGet(-addAndGet)) > 0L);
            this.tryUnsubscribe();
        }
        
        private boolean tryUnsubscribe() {
            if (this.hasTerminated || this.get() < -1L) {
                this.set(-1L);
                this.doUnsubscribe();
                return true;
            }
            return false;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() < 0L;
        }
        
        @Override
        public void onCompleted() {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            if (!this.actualSubscriber.isUnsubscribed()) {
                this.actualSubscriber.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            if (!this.actualSubscriber.isUnsubscribed()) {
                this.actualSubscriber.onError(t);
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (this.onNextCalled) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.onNextCalled = true;
            this.actualSubscriber.onNext((Object)t);
        }
        
        @Override
        public void request(final long n) {
            if (n > 0L && BackpressureUtils.getAndAddRequest(this, n) == 0L) {
                if (n != Long.MAX_VALUE) {
                    this.slowPath(n);
                    return;
                }
                this.fastPath();
            }
        }
        
        @Override
        public void unsubscribe() {
            long value;
            do {
                value = this.get();
                if (this.compareAndSet(0L, -1L)) {
                    this.doUnsubscribe();
                }
            } while (!this.compareAndSet(value, -2L));
        }
    }
    
    static final class SyncOnSubscribeImpl<S, T> extends SyncOnSubscribe<S, T>
    {
        private final Func0<? extends S> generator;
        private final Func2<? super S, ? super Observer<? super T>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;
        
        public SyncOnSubscribeImpl(final Func0<? extends S> func0, final Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
            this(func0, func2, null);
        }
        
        SyncOnSubscribeImpl(final Func0<? extends S> generator, final Func2<? super S, ? super Observer<? super T>, ? extends S> next, final Action1<? super S> onUnsubscribe) {
            this.generator = generator;
            this.next = next;
            this.onUnsubscribe = onUnsubscribe;
        }
        
        public SyncOnSubscribeImpl(final Func2<S, Observer<? super T>, S> func2) {
            this(null, (Func2)func2, null);
        }
        
        public SyncOnSubscribeImpl(final Func2<S, Observer<? super T>, S> func2, final Action1<? super S> action1) {
            this(null, (Func2)func2, action1);
        }
        
        @Override
        protected S generateState() {
            if (this.generator == null) {
                return null;
            }
            return (S)this.generator.call();
        }
        
        @Override
        protected S next(final S n, final Observer<? super T> observer) {
            return (S)this.next.call((Object)n, observer);
        }
        
        @Override
        protected void onUnsubscribe(final S n) {
            if (this.onUnsubscribe != null) {
                this.onUnsubscribe.call((Object)n);
            }
        }
    }
}
