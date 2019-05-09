// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicReference;
import rx.annotations.Experimental;
import rx.Subscription;
import rx.CompletableSubscriber;

@Experimental
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription
{
    static final Unsubscribed UNSUBSCRIBED;
    private final AtomicReference<Subscription> upstream;
    
    static {
        UNSUBSCRIBED = new Unsubscribed();
    }
    
    public AsyncCompletableSubscriber() {
        this.upstream = new AtomicReference<Subscription>();
    }
    
    protected final void clear() {
        this.upstream.set(AsyncCompletableSubscriber.UNSUBSCRIBED);
    }
    
    @Override
    public final boolean isUnsubscribed() {
        return this.upstream.get() == AsyncCompletableSubscriber.UNSUBSCRIBED;
    }
    
    protected void onStart() {
    }
    
    @Override
    public final void onSubscribe(final Subscription subscription) {
        if (!this.upstream.compareAndSet(null, subscription)) {
            subscription.unsubscribe();
            if (this.upstream.get() != AsyncCompletableSubscriber.UNSUBSCRIBED) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
            }
            return;
        }
        this.onStart();
    }
    
    @Override
    public final void unsubscribe() {
        if (this.upstream.get() != AsyncCompletableSubscriber.UNSUBSCRIBED) {
            final Unsubscribed unsubscribed = this.upstream.getAndSet(AsyncCompletableSubscriber.UNSUBSCRIBED);
            if (unsubscribed != null && unsubscribed != AsyncCompletableSubscriber.UNSUBSCRIBED) {
                unsubscribed.unsubscribe();
            }
        }
    }
    
    static final class Unsubscribed implements Subscription
    {
        @Override
        public boolean isUnsubscribed() {
            return true;
        }
        
        @Override
        public void unsubscribe() {
        }
    }
}
