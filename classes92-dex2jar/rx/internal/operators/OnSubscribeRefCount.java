// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action1;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicInteger;
import rx.observables.ConnectableObservable;
import java.util.concurrent.locks.ReentrantLock;
import rx.subscriptions.CompositeSubscription;
import rx.Observable;

public final class OnSubscribeRefCount<T> implements OnSubscribe<T>
{
    volatile CompositeSubscription baseSubscription;
    final ReentrantLock lock;
    private final ConnectableObservable<? extends T> source;
    final AtomicInteger subscriptionCount;
    
    public OnSubscribeRefCount(final ConnectableObservable<? extends T> source) {
        this.baseSubscription = new CompositeSubscription();
        this.subscriptionCount = new AtomicInteger(0);
        this.lock = new ReentrantLock();
        this.source = source;
    }
    
    private Subscription disconnect(final CompositeSubscription compositeSubscription) {
        return Subscriptions.create(new Action0() {
            @Override
            public void call() {
                OnSubscribeRefCount.this.lock.lock();
                try {
                    if (OnSubscribeRefCount.this.baseSubscription == compositeSubscription && OnSubscribeRefCount.this.subscriptionCount.decrementAndGet() == 0) {
                        OnSubscribeRefCount.this.baseSubscription.unsubscribe();
                        OnSubscribeRefCount.this.baseSubscription = new CompositeSubscription();
                    }
                }
                finally {
                    OnSubscribeRefCount.this.lock.unlock();
                }
            }
        });
    }
    
    private Action1<Subscription> onSubscribe(final Subscriber<? super T> subscriber, final AtomicBoolean atomicBoolean) {
        return new Action1<Subscription>() {
            @Override
            public void call(final Subscription subscription) {
                try {
                    OnSubscribeRefCount.this.baseSubscription.add(subscription);
                    OnSubscribeRefCount.this.doSubscribe(subscriber, OnSubscribeRefCount.this.baseSubscription);
                }
                finally {
                    OnSubscribeRefCount.this.lock.unlock();
                    atomicBoolean.set(false);
                }
            }
        };
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        this.lock.lock();
        if (this.subscriptionCount.incrementAndGet() == 1) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.source.connect(this.onSubscribe(subscriber, atomicBoolean));
                return;
            }
            finally {
                if (atomicBoolean.get()) {
                    this.lock.unlock();
                }
            }
        }
        try {
            this.doSubscribe(subscriber, this.baseSubscription);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    void doSubscribe(final Subscriber<? super T> subscriber, final CompositeSubscription compositeSubscription) {
        subscriber.add(this.disconnect(compositeSubscription));
        this.source.unsafeSubscribe(new Subscriber<T>(subscriber) {
            void cleanup() {
                OnSubscribeRefCount.this.lock.lock();
                try {
                    if (OnSubscribeRefCount.this.baseSubscription == compositeSubscription) {
                        OnSubscribeRefCount.this.baseSubscription.unsubscribe();
                        OnSubscribeRefCount.this.baseSubscription = new CompositeSubscription();
                        OnSubscribeRefCount.this.subscriptionCount.set(0);
                    }
                }
                finally {
                    OnSubscribeRefCount.this.lock.unlock();
                }
            }
            
            @Override
            public void onCompleted() {
                this.cleanup();
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                this.cleanup();
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                subscriber.onNext(t);
            }
        });
    }
}
