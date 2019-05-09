// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeMergeIterable implements OnSubscribe
{
    final Iterable<? extends Completable> sources;
    
    public CompletableOnSubscribeMergeIterable(final Iterable<? extends Completable> sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        Object o = null;
    Label_0249:
        while (true) {
            o = new CompositeSubscription();
            completableSubscriber.onSubscribe((Subscription)o);
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
            final AtomicBoolean atomicBoolean = new AtomicBoolean();
            while (!((CompositeSubscription)o).isUnsubscribed()) {
                try {
                    if (!iterator.hasNext()) {
                        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onCompleted();
                        }
                        return;
                    }
                }
                catch (Throwable t2) {
                    ((CompositeSubscription)o).unsubscribe();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onError(t2);
                        return;
                    }
                    RxJavaHooks.onError(t2);
                    return;
                }
                if (((CompositeSubscription)o).isUnsubscribed()) {
                    return;
                }
                Completable completable = null;
                Label_0255: {
                    try {
                        completable = iterator.next();
                        if (((CompositeSubscription)o).isUnsubscribed()) {
                            return;
                        }
                        if (completable != null) {
                            break Label_0255;
                        }
                        ((CompositeSubscription)o).unsubscribe();
                        o = new NullPointerException("A completable source is null");
                        if (atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onError((Throwable)o);
                            return;
                        }
                    }
                    catch (Throwable t3) {
                        ((CompositeSubscription)o).unsubscribe();
                        if (atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onError(t3);
                            return;
                        }
                        RxJavaHooks.onError(t3);
                        return;
                    }
                    break Label_0249;
                }
                atomicInteger.getAndIncrement();
                completable.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onCompleted();
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        ((CompositeSubscription)o).unsubscribe();
                        if (atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onError(t);
                            return;
                        }
                        RxJavaHooks.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        ((CompositeSubscription)o).add(subscription);
                    }
                });
            }
            return;
        }
        RxJavaHooks.onError((Throwable)o);
    }
}
