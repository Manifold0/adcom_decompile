// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subscriptions.CompositeSubscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscriber;
import rx.Subscription;
import rx.CompletableSubscriber;
import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.ArrayList;
import java.util.Queue;
import rx.Observable;
import rx.Completable;

public final class CompletableOnSubscribeMerge implements OnSubscribe
{
    final boolean delayErrors;
    final int maxConcurrency;
    final Observable<Completable> source;
    
    public CompletableOnSubscribeMerge(final Observable<? extends Completable> source, final int maxConcurrency, final boolean delayErrors) {
        this.source = (Observable<Completable>)source;
        this.maxConcurrency = maxConcurrency;
        this.delayErrors = delayErrors;
    }
    
    public static Throwable collectErrors(final Queue<Throwable> queue) {
        final ArrayList<Throwable> list = new ArrayList<Throwable>();
        while (true) {
            final Throwable t = queue.poll();
            if (t == null) {
                break;
            }
            list.add(t);
        }
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return (Throwable)list.get(0);
        }
        return new CompositeException(list);
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompletableMergeSubscriber completableMergeSubscriber = new CompletableMergeSubscriber(completableSubscriber, this.maxConcurrency, this.delayErrors);
        completableSubscriber.onSubscribe(completableMergeSubscriber);
        this.source.subscribe(completableMergeSubscriber);
    }
    
    static final class CompletableMergeSubscriber extends Subscriber<Completable>
    {
        final CompletableSubscriber actual;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicReference<Queue<Throwable>> errors;
        final AtomicBoolean once;
        final CompositeSubscription set;
        final AtomicInteger wip;
        
        public CompletableMergeSubscriber(final CompletableSubscriber actual, final int n, final boolean delayErrors) {
            this.actual = actual;
            this.delayErrors = delayErrors;
            this.set = new CompositeSubscription();
            this.wip = new AtomicInteger(1);
            this.once = new AtomicBoolean();
            this.errors = new AtomicReference<Queue<Throwable>>();
            if (n == Integer.MAX_VALUE) {
                this.request(Long.MAX_VALUE);
                return;
            }
            this.request(n);
        }
        
        Queue<Throwable> getOrCreateErrors() {
            final Queue<Throwable> queue = this.errors.get();
            if (queue != null) {
                return queue;
            }
            final ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = new ConcurrentLinkedQueue<Throwable>();
            if (this.errors.compareAndSet(null, concurrentLinkedQueue)) {
                return concurrentLinkedQueue;
            }
            return this.errors.get();
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.terminate();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.getOrCreateErrors().offer(t);
            this.done = true;
            this.terminate();
        }
        
        @Override
        public void onNext(final Completable completable) {
            if (this.done) {
                return;
            }
            this.wip.getAndIncrement();
            completable.unsafeSubscribe(new CompletableSubscriber() {
                Subscription d;
                boolean innerDone;
                
                @Override
                public void onCompleted() {
                    if (!this.innerDone) {
                        this.innerDone = true;
                        CompletableMergeSubscriber.this.set.remove(this.d);
                        CompletableMergeSubscriber.this.terminate();
                        if (!CompletableMergeSubscriber.this.done) {
                            Subscriber.this.request(1L);
                        }
                    }
                }
                
                @Override
                public void onError(final Throwable t) {
                    if (this.innerDone) {
                        RxJavaHooks.onError(t);
                    }
                    else {
                        this.innerDone = true;
                        CompletableMergeSubscriber.this.set.remove(this.d);
                        CompletableMergeSubscriber.this.getOrCreateErrors().offer(t);
                        CompletableMergeSubscriber.this.terminate();
                        if (CompletableMergeSubscriber.this.delayErrors && !CompletableMergeSubscriber.this.done) {
                            Subscriber.this.request(1L);
                        }
                    }
                }
                
                @Override
                public void onSubscribe(final Subscription d) {
                    this.d = d;
                    CompletableMergeSubscriber.this.set.add(d);
                }
            });
        }
        
        void terminate() {
            if (this.wip.decrementAndGet() == 0) {
                final Queue<Throwable> queue = this.errors.get();
                if (queue == null || queue.isEmpty()) {
                    this.actual.onCompleted();
                }
                else {
                    final Throwable collectErrors = CompletableOnSubscribeMerge.collectErrors(queue);
                    if (this.once.compareAndSet(false, true)) {
                        this.actual.onError(collectErrors);
                        return;
                    }
                    RxJavaHooks.onError(collectErrors);
                }
            }
            else if (!this.delayErrors) {
                final Queue<Throwable> queue2 = this.errors.get();
                if (queue2 != null && !queue2.isEmpty()) {
                    final Throwable collectErrors2 = CompletableOnSubscribeMerge.collectErrors(queue2);
                    if (this.once.compareAndSet(false, true)) {
                        this.actual.onError(collectErrors2);
                        return;
                    }
                    RxJavaHooks.onError(collectErrors2);
                }
            }
        }
    }
}
