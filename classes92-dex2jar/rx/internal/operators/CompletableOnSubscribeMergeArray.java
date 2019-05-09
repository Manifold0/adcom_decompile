// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subscriptions.CompositeSubscription;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeMergeArray implements OnSubscribe
{
    final Completable[] sources;
    
    public CompletableOnSubscribeMergeArray(final Completable[] sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        final AtomicInteger atomicInteger = new AtomicInteger(this.sources.length + 1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        completableSubscriber.onSubscribe(compositeSubscription);
        final Completable[] sources = this.sources;
        for (int length = sources.length, i = 0; i < length; ++i) {
            final Completable completable = sources[i];
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                compositeSubscription.unsubscribe();
                final NullPointerException ex = new NullPointerException("A completable source is null");
                if (atomicBoolean.compareAndSet(false, true)) {
                    completableSubscriber.onError(ex);
                    return;
                }
                RxJavaHooks.onError(ex);
            }
            completable.unsafeSubscribe(new CompletableSubscriber() {
                @Override
                public void onCompleted() {
                    if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onCompleted();
                    }
                }
                
                @Override
                public void onError(final Throwable t) {
                    compositeSubscription.unsubscribe();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onError(t);
                        return;
                    }
                    RxJavaHooks.onError(t);
                }
                
                @Override
                public void onSubscribe(final Subscription subscription) {
                    compositeSubscription.add(subscription);
                }
            });
        }
        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
            completableSubscriber.onCompleted();
        }
    }
}
