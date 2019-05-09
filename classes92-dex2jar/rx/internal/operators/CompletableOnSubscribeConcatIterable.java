// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.subscriptions.SerialSubscription;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Iterator;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeConcatIterable implements OnSubscribe
{
    final Iterable<? extends Completable> sources;
    
    public CompletableOnSubscribeConcatIterable(final Iterable<? extends Completable> sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        Iterator<? extends Completable> iterator;
        try {
            iterator = this.sources.iterator();
            if (iterator == null) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(new NullPointerException("The iterator returned is null"));
                return;
            }
        }
        catch (Throwable t) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onError(t);
            return;
        }
        final ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, iterator);
        completableSubscriber.onSubscribe(concatInnerSubscriber.sd);
        concatInnerSubscriber.next();
    }
    
    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber
    {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        final SerialSubscription sd;
        final Iterator<? extends Completable> sources;
        
        public ConcatInnerSubscriber(final CompletableSubscriber actual, final Iterator<? extends Completable> sources) {
            this.actual = actual;
            this.sources = sources;
            this.sd = new SerialSubscription();
        }
        
        void next() {
            if (!this.sd.isUnsubscribed() && this.getAndIncrement() == 0) {
                final Iterator<? extends Completable> sources = this.sources;
                while (!this.sd.isUnsubscribed()) {
                    try {
                        if (!sources.hasNext()) {
                            this.actual.onCompleted();
                            return;
                        }
                    }
                    catch (Throwable t) {
                        this.actual.onError(t);
                        return;
                    }
                    Completable completable;
                    try {
                        completable = sources.next();
                        if (completable == null) {
                            this.actual.onError(new NullPointerException("The completable returned is null"));
                            return;
                        }
                    }
                    catch (Throwable t2) {
                        this.actual.onError(t2);
                        return;
                    }
                    completable.unsafeSubscribe(this);
                    if (this.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
        
        @Override
        public void onCompleted() {
            this.next();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.actual.onError(t);
        }
        
        @Override
        public void onSubscribe(final Subscription subscription) {
            this.sd.set(subscription);
        }
    }
}
