// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Queue;
import rx.Subscription;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subscriptions.CompositeSubscription;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeMergeDelayErrorArray implements OnSubscribe
{
    final Completable[] sources;
    
    public CompletableOnSubscribeMergeDelayErrorArray(final Completable[] sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        final AtomicInteger atomicInteger = new AtomicInteger(this.sources.length + 1);
        final ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = new ConcurrentLinkedQueue<Throwable>();
        completableSubscriber.onSubscribe(compositeSubscription);
        final Completable[] sources = this.sources;
        for (int length = sources.length, i = 0; i < length; ++i) {
            final Completable completable = sources[i];
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                concurrentLinkedQueue.offer(new NullPointerException("A completable source is null"));
                atomicInteger.decrementAndGet();
            }
            else {
                completable.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        this.tryTerminate();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        concurrentLinkedQueue.offer(t);
                        this.tryTerminate();
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        compositeSubscription.add(subscription);
                    }
                    
                    void tryTerminate() {
                        if (atomicInteger.decrementAndGet() == 0) {
                            if (!concurrentLinkedQueue.isEmpty()) {
                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(concurrentLinkedQueue));
                                return;
                            }
                            completableSubscriber.onCompleted();
                        }
                    }
                });
            }
        }
        if (atomicInteger.decrementAndGet() != 0) {
            return;
        }
        if (concurrentLinkedQueue.isEmpty()) {
            completableSubscriber.onCompleted();
            return;
        }
        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(concurrentLinkedQueue));
    }
}
