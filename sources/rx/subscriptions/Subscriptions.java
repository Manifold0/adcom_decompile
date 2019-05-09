package rx.subscriptions;

import java.util.concurrent.Future;
import rx.Subscription;
import rx.functions.Action0;

public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    static final class FutureSubscription implements Subscription {
        /* renamed from: f */
        final Future<?> f8393f;

        public FutureSubscription(Future<?> f) {
            this.f8393f = f;
        }

        public void unsubscribe() {
            this.f8393f.cancel(true);
        }

        public boolean isUnsubscribed() {
            return this.f8393f.isCancelled();
        }
    }

    static final class Unsubscribed implements Subscription {
        Unsubscribed() {
        }

        public void unsubscribe() {
        }

        public boolean isUnsubscribed() {
            return true;
        }
    }

    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription empty() {
        return BooleanSubscription.create();
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(Action0 unsubscribe) {
        return BooleanSubscription.create(unsubscribe);
    }

    public static Subscription from(Future<?> f) {
        return new FutureSubscription(f);
    }

    public static CompositeSubscription from(Subscription... subscriptions) {
        return new CompositeSubscription(subscriptions);
    }
}
