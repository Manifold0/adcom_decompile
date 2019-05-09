// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.Iterator;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;

public final class SubscriptionList implements Subscription
{
    private List<Subscription> subscriptions;
    private volatile boolean unsubscribed;
    
    public SubscriptionList() {
    }
    
    public SubscriptionList(final Subscription subscription) {
        (this.subscriptions = new LinkedList<Subscription>()).add(subscription);
    }
    
    public SubscriptionList(final Subscription... array) {
        this.subscriptions = new LinkedList<Subscription>(Arrays.asList(array));
    }
    
    private static void unsubscribeFromAll(Collection<Subscription> o) {
        if (o == null) {
            return;
        }
        final Collection<Subscription> collection = null;
        final Iterator<Subscription> iterator = ((Collection<Subscription>)o).iterator();
        o = collection;
        while (iterator.hasNext()) {
            final Subscription subscription = iterator.next();
            try {
                subscription.unsubscribe();
            }
            catch (Throwable t) {
                Collection<Subscription> collection2 = (Collection<Subscription>)o;
                if (o == null) {
                    collection2 = new ArrayList<Subscription>();
                }
                ((List<Subscription>)collection2).add((Subscription)t);
                o = collection2;
            }
        }
        Exceptions.throwIfAny((List<? extends Throwable>)o);
    }
    
    public void add(final Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    List<Subscription> subscriptions;
                    if ((subscriptions = this.subscriptions) == null) {
                        subscriptions = new LinkedList<Subscription>();
                        this.subscriptions = subscriptions;
                    }
                    subscriptions.add(subscription);
                    return;
                }
            }
        }
        // monitorexit(this)
        subscription.unsubscribe();
    }
    
    public void clear() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            final List<Subscription> subscriptions = this.subscriptions;
            this.subscriptions = null;
            // monitorexit(this)
            unsubscribeFromAll(subscriptions);
        }
    }
    
    public boolean hasSubscriptions() {
        final boolean b = false;
        if (!this.unsubscribed) {
            // monitorenter(this)
            boolean b2 = b;
            try {
                if (!this.unsubscribed) {
                    b2 = b;
                    if (this.subscriptions != null) {
                        b2 = b;
                        if (!this.subscriptions.isEmpty()) {
                            b2 = true;
                        }
                    }
                }
                return b2;
            }
            finally {
            }
            // monitorexit(this)
        }
        return false;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }
    
    public void remove(final Subscription subscription) {
        if (!this.unsubscribed) {
            synchronized (this) {
                final List<Subscription> subscriptions = this.subscriptions;
                if (this.unsubscribed || subscriptions == null) {
                    return;
                }
                final boolean remove = subscriptions.remove(subscription);
                // monitorexit(this)
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }
    
    @Override
    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                final List<Subscription> subscriptions = this.subscriptions;
                this.subscriptions = null;
                // monitorexit(this)
                unsubscribeFromAll(subscriptions);
            }
        }
    }
}
