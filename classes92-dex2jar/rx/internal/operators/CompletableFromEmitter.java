// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.AsyncEmitter;
import rx.plugins.RxJavaHooks;
import rx.internal.subscriptions.SequentialSubscription;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.exceptions.Exceptions;
import rx.Subscription;
import rx.CompletableSubscriber;
import rx.CompletableEmitter;
import rx.functions.Action1;
import rx.Completable;

public final class CompletableFromEmitter implements OnSubscribe
{
    final Action1<CompletableEmitter> producer;
    
    public CompletableFromEmitter(final Action1<CompletableEmitter> producer) {
        this.producer = producer;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final FromEmitter fromEmitter = new FromEmitter(completableSubscriber);
        completableSubscriber.onSubscribe(fromEmitter);
        try {
            this.producer.call(fromEmitter);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            fromEmitter.onError(t);
        }
    }
    
    static final class FromEmitter extends AtomicBoolean implements CompletableEmitter, Subscription
    {
        private static final long serialVersionUID = 5539301318568668881L;
        final CompletableSubscriber actual;
        final SequentialSubscription resource;
        
        public FromEmitter(final CompletableSubscriber actual) {
            this.actual = actual;
            this.resource = new SequentialSubscription();
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get();
        }
        
        @Override
        public void onCompleted() {
            if (!this.compareAndSet(false, true)) {
                return;
            }
            try {
                this.actual.onCompleted();
            }
            finally {
                this.resource.unsubscribe();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.compareAndSet(false, true)) {
                try {
                    this.actual.onError(t);
                    return;
                }
                finally {
                    this.resource.unsubscribe();
                }
            }
            RxJavaHooks.onError(t);
        }
        
        @Override
        public void setCancellation(final AsyncEmitter.Cancellable cancellable) {
            this.setSubscription(new OnSubscribeFromEmitter.CancellableSubscription(cancellable));
        }
        
        @Override
        public void setSubscription(final Subscription subscription) {
            this.resource.update(subscription);
        }
        
        @Override
        public void unsubscribe() {
            if (this.compareAndSet(false, true)) {
                this.resource.unsubscribe();
            }
        }
    }
}
