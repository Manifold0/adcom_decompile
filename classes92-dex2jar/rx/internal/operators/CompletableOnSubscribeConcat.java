// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.MissingBackpressureException;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subscriptions.SerialSubscription;
import rx.internal.util.unsafe.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.Subscription;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Completable;

public final class CompletableOnSubscribeConcat implements OnSubscribe
{
    final int prefetch;
    final Observable<Completable> sources;
    
    public CompletableOnSubscribeConcat(final Observable<? extends Completable> sources, final int prefetch) {
        this.sources = (Observable<Completable>)sources;
        this.prefetch = prefetch;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompletableConcatSubscriber completableConcatSubscriber = new CompletableConcatSubscriber(completableSubscriber, this.prefetch);
        completableSubscriber.onSubscribe(completableConcatSubscriber);
        this.sources.subscribe(completableConcatSubscriber);
    }
    
    static final class CompletableConcatSubscriber extends Subscriber<Completable>
    {
        final CompletableSubscriber actual;
        volatile boolean done;
        final ConcatInnerSubscriber inner;
        final AtomicBoolean once;
        final SpscArrayQueue<Completable> queue;
        final SerialSubscription sr;
        final AtomicInteger wip;
        
        public CompletableConcatSubscriber(final CompletableSubscriber actual, final int n) {
            this.actual = actual;
            this.queue = new SpscArrayQueue<Completable>(n);
            this.sr = new SerialSubscription();
            this.inner = new ConcatInnerSubscriber();
            this.wip = new AtomicInteger();
            this.once = new AtomicBoolean();
            this.add(this.sr);
            this.request(n);
        }
        
        void innerComplete() {
            if (this.wip.decrementAndGet() != 0) {
                this.next();
            }
            if (!this.done) {
                this.request(1L);
            }
        }
        
        void innerError(final Throwable t) {
            this.unsubscribe();
            this.onError(t);
        }
        
        void next() {
            final boolean done = this.done;
            final Completable completable = this.queue.poll();
            if (completable != null) {
                completable.unsafeSubscribe(this.inner);
                return;
            }
            if (done) {
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onCompleted();
                }
                return;
            }
            RxJavaHooks.onError(new IllegalStateException("Queue is empty?!"));
        }
        
        @Override
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                if (this.wip.getAndIncrement() == 0) {
                    this.next();
                }
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(t);
                return;
            }
            RxJavaHooks.onError(t);
        }
        
        @Override
        public void onNext(final Completable completable) {
            if (!this.queue.offer(completable)) {
                this.onError(new MissingBackpressureException());
            }
            else if (this.wip.getAndIncrement() == 0) {
                this.next();
            }
        }
        
        final class ConcatInnerSubscriber implements CompletableSubscriber
        {
            @Override
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
            
            @Override
            public void onError(final Throwable t) {
                CompletableConcatSubscriber.this.innerError(t);
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
                CompletableConcatSubscriber.this.sr.set(subscription);
            }
        }
    }
}
