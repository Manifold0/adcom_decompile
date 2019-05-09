// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.subscriptions;

import rx.subscriptions.Subscriptions;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicReference;

public final class SequentialSubscription extends AtomicReference<Subscription> implements Subscription
{
    private static final long serialVersionUID = 995205034283130269L;
    
    public SequentialSubscription() {
    }
    
    public SequentialSubscription(final Subscription subscription) {
        this.lazySet(subscription);
    }
    
    public Subscription current() {
        Subscription unsubscribed;
        if ((unsubscribed = super.get()) == Unsubscribed.INSTANCE) {
            unsubscribed = Subscriptions.unsubscribed();
        }
        return unsubscribed;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.get() == Unsubscribed.INSTANCE;
    }
    
    public boolean replace(final Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = this.get();
            if (subscription2 == Unsubscribed.INSTANCE) {
                if (subscription != null) {
                    subscription.unsubscribe();
                }
                return false;
            }
        } while (!this.compareAndSet(subscription2, subscription));
        return true;
    }
    
    public boolean replaceWeak(final Subscription subscription) {
        final Subscription subscription2 = this.get();
        if (subscription2 == Unsubscribed.INSTANCE) {
            if (subscription != null) {
                subscription.unsubscribe();
            }
        }
        else {
            if (this.compareAndSet(subscription2, subscription)) {
                return true;
            }
            if (this.get() != Unsubscribed.INSTANCE) {
                return true;
            }
            if (subscription != null) {
                subscription.unsubscribe();
                return false;
            }
        }
        return false;
    }
    
    @Override
    public void unsubscribe() {
        if (this.get() != Unsubscribed.INSTANCE) {
            final Unsubscribed unsubscribed = ((AtomicReference<Unsubscribed>)this).getAndSet(Unsubscribed.INSTANCE);
            if (unsubscribed != null && unsubscribed != Unsubscribed.INSTANCE) {
                unsubscribed.unsubscribe();
            }
        }
    }
    
    public boolean update(final Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = this.get();
            if (subscription2 == Unsubscribed.INSTANCE) {
                if (subscription != null) {
                    subscription.unsubscribe();
                }
                return false;
            }
        } while (!this.compareAndSet(subscription2, subscription));
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        return true;
    }
    
    public boolean updateWeak(final Subscription subscription) {
        final boolean b = true;
        final Subscription subscription2 = this.get();
        boolean b2;
        if (subscription2 == Unsubscribed.INSTANCE) {
            if (subscription != null) {
                subscription.unsubscribe();
            }
            b2 = false;
        }
        else {
            b2 = b;
            if (!this.compareAndSet(subscription2, subscription)) {
                final Subscription subscription3 = this.get();
                if (subscription != null) {
                    subscription.unsubscribe();
                }
                b2 = b;
                if (subscription3 != Unsubscribed.INSTANCE) {
                    return false;
                }
            }
        }
        return b2;
    }
}
