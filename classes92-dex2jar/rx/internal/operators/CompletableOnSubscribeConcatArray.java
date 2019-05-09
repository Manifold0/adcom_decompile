// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.subscriptions.SerialSubscription;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscription;
import rx.CompletableSubscriber;
import rx.Completable;

public final class CompletableOnSubscribeConcatArray implements OnSubscribe
{
    final Completable[] sources;
    
    public CompletableOnSubscribeConcatArray(final Completable[] sources) {
        this.sources = sources;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, this.sources);
        completableSubscriber.onSubscribe(concatInnerSubscriber.sd);
        concatInnerSubscriber.next();
    }
    
    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber
    {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        int index;
        final SerialSubscription sd;
        final Completable[] sources;
        
        public ConcatInnerSubscriber(final CompletableSubscriber actual, final Completable[] sources) {
            this.actual = actual;
            this.sources = sources;
            this.sd = new SerialSubscription();
        }
        
        void next() {
            if (!this.sd.isUnsubscribed() && this.getAndIncrement() == 0) {
                final Completable[] sources = this.sources;
                while (!this.sd.isUnsubscribed()) {
                    final int n = this.index++;
                    if (n == sources.length) {
                        this.actual.onCompleted();
                        return;
                    }
                    sources[n].unsafeSubscribe(this);
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
