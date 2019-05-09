// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import java.util.concurrent.TimeoutException;
import rx.functions.Action0;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.CompletableSubscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Completable;

public final class CompletableOnSubscribeTimeout implements OnSubscribe
{
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;
    
    public CompletableOnSubscribeTimeout(final Completable source, final long timeout, final TimeUnit unit, final Scheduler scheduler, final Completable other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final Scheduler.Worker worker = this.scheduler.createWorker();
        compositeSubscription.add(worker);
        worker.schedule(new Action0() {
            @Override
            public void call() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.clear();
                    if (CompletableOnSubscribeTimeout.this.other != null) {
                        CompletableOnSubscribeTimeout.this.other.unsafeSubscribe(new CompletableSubscriber() {
                            @Override
                            public void onCompleted() {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onCompleted();
                            }
                            
                            @Override
                            public void onError(final Throwable t) {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onError(t);
                            }
                            
                            @Override
                            public void onSubscribe(final Subscription subscription) {
                                compositeSubscription.add(subscription);
                            }
                        });
                        return;
                    }
                    completableSubscriber.onError(new TimeoutException());
                }
            }
        }, this.timeout, this.unit);
        this.source.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.unsubscribe();
                    completableSubscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.unsubscribe();
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
}
