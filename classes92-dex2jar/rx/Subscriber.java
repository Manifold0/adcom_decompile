// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.internal.util.SubscriptionList;

public abstract class Subscriber<T> implements Observer<T>, Subscription
{
    private static final long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;
    
    protected Subscriber() {
        this(null, false);
    }
    
    protected Subscriber(final Subscriber<?> subscriber) {
        this(subscriber, true);
    }
    
    protected Subscriber(final Subscriber<?> subscriber, final boolean b) {
        this.requested = Long.MIN_VALUE;
        this.subscriber = subscriber;
        SubscriptionList subscriptions;
        if (b && subscriber != null) {
            subscriptions = subscriber.subscriptions;
        }
        else {
            subscriptions = new SubscriptionList();
        }
        this.subscriptions = subscriptions;
    }
    
    private void addToRequested(long n) {
        if (this.requested == Long.MIN_VALUE) {
            this.requested = n;
            return;
        }
        n += this.requested;
        if (n < 0L) {
            this.requested = Long.MAX_VALUE;
            return;
        }
        this.requested = n;
    }
    
    public final void add(final Subscription subscription) {
        this.subscriptions.add(subscription);
    }
    
    @Override
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }
    
    public void onStart() {
    }
    
    protected final void request(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("number requested cannot be negative: " + n);
        }
        synchronized (this) {
            if (this.producer != null) {
                final Producer producer = this.producer;
                // monitorexit(this)
                producer.request(n);
                return;
            }
            this.addToRequested(n);
        }
    }
    
    public void setProducer(final Producer producer) {
        final boolean b = false;
        final long requested;
        synchronized (this) {
            requested = this.requested;
            this.producer = producer;
            int n = b ? 1 : 0;
            if (this.subscriber != null) {
                n = (b ? 1 : 0);
                if (requested == Long.MIN_VALUE) {
                    n = 1;
                }
            }
            // monitorexit(this)
            if (n != 0) {
                this.subscriber.setProducer(this.producer);
                return;
            }
        }
        if (requested == Long.MIN_VALUE) {
            this.producer.request(Long.MAX_VALUE);
            return;
        }
        this.producer.request(requested);
    }
    
    @Override
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }
}
