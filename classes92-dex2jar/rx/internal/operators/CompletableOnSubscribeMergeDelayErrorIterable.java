// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeMergeDelayErrorIterable implements OnSubscribe
{
    final Iterable<? extends Completable> sources;
    
    public CompletableOnSubscribeMergeDelayErrorIterable(final Iterable<? extends Completable> sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        while (true) {
            final CompositeSubscription compositeSubscription = new CompositeSubscription();
            completableSubscriber.onSubscribe(compositeSubscription);
            Iterator<? extends Completable> iterator;
            try {
                iterator = this.sources.iterator();
                if (iterator == null) {
                    completableSubscriber.onError(new NullPointerException("The source iterator returned is null"));
                    return;
                }
            }
            catch (Throwable t) {
                completableSubscriber.onError(t);
                return;
            }
            final AtomicInteger atomicInteger = new AtomicInteger(1);
            final MpscLinkedQueue<NullPointerException> mpscLinkedQueue = new MpscLinkedQueue<NullPointerException>();
            while (!compositeSubscription.isUnsubscribed()) {
                Label_0329: {
                    try {
                        if (!iterator.hasNext()) {
                            if (atomicInteger.decrementAndGet() != 0) {
                                return;
                            }
                            if (mpscLinkedQueue.isEmpty()) {
                                completableSubscriber.onCompleted();
                                return;
                            }
                            break Label_0329;
                        }
                    }
                    catch (Throwable t2) {
                        mpscLinkedQueue.offer((NullPointerException)t2);
                        if (atomicInteger.decrementAndGet() != 0) {
                            return;
                        }
                        if (mpscLinkedQueue.isEmpty()) {
                            completableSubscriber.onCompleted();
                            return;
                        }
                        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors((Queue<Throwable>)mpscLinkedQueue));
                        return;
                    }
                    if (!compositeSubscription.isUnsubscribed()) {
                        Label_0290: {
                            Completable completable = null;
                            Label_0302: {
                                try {
                                    completable = iterator.next();
                                    if (compositeSubscription.isUnsubscribed()) {
                                        return;
                                    }
                                    if (completable != null) {
                                        break Label_0302;
                                    }
                                    mpscLinkedQueue.offer(new NullPointerException("A completable source is null"));
                                    if (atomicInteger.decrementAndGet() != 0) {
                                        return;
                                    }
                                    if (mpscLinkedQueue.isEmpty()) {
                                        completableSubscriber.onCompleted();
                                        return;
                                    }
                                }
                                catch (Throwable t3) {
                                    mpscLinkedQueue.offer((NullPointerException)t3);
                                    if (atomicInteger.decrementAndGet() != 0) {
                                        return;
                                    }
                                    if (mpscLinkedQueue.isEmpty()) {
                                        completableSubscriber.onCompleted();
                                        return;
                                    }
                                    completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors((Queue<Throwable>)mpscLinkedQueue));
                                    return;
                                }
                                break Label_0290;
                            }
                            atomicInteger.getAndIncrement();
                            completable.unsafeSubscribe(new CompletableSubscriber() {
                                @Override
                                public void onCompleted() {
                                    this.tryTerminate();
                                }
                                
                                @Override
                                public void onError(final Throwable t) {
                                    mpscLinkedQueue.offer(t);
                                    this.tryTerminate();
                                }
                                
                                @Override
                                public void onSubscribe(final Subscription subscription) {
                                    compositeSubscription.add(subscription);
                                }
                                
                                void tryTerminate() {
                                    if (atomicInteger.decrementAndGet() == 0) {
                                        if (!mpscLinkedQueue.isEmpty()) {
                                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                                            return;
                                        }
                                        completableSubscriber.onCompleted();
                                    }
                                }
                            });
                            continue;
                        }
                        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors((Queue<Throwable>)mpscLinkedQueue));
                        return;
                    }
                    return;
                }
                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors((Queue<Throwable>)mpscLinkedQueue));
            }
        }
    }
}
