package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.internal.util.SubscriptionList;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    private static final long serialVersionUID = -3962399486978279857L;
    final Action0 action;
    final SubscriptionList cancel;

    final class FutureCompleter implements Subscription {
        /* renamed from: f */
        private final Future<?> f8385f;

        FutureCompleter(Future<?> f) {
            this.f8385f = f;
        }

        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f8385f.cancel(true);
            } else {
                this.f8385f.cancel(false);
            }
        }

        public boolean isUnsubscribed() {
            return this.f8385f.isCancelled();
        }
    }

    static final class Remover2 extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final SubscriptionList parent;
        /* renamed from: s */
        final ScheduledAction f8386s;

        public Remover2(ScheduledAction s, SubscriptionList parent) {
            this.f8386s = s;
            this.parent = parent;
        }

        public boolean isUnsubscribed() {
            return this.f8386s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f8386s);
            }
        }
    }

    static final class Remover extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final CompositeSubscription parent;
        /* renamed from: s */
        final ScheduledAction f8387s;

        public Remover(ScheduledAction s, CompositeSubscription parent) {
            this.f8387s = s;
            this.parent = parent;
        }

        public boolean isUnsubscribed() {
            return this.f8387s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f8387s);
            }
        }
    }

    public ScheduledAction(Action0 action) {
        this.action = action;
        this.cancel = new SubscriptionList();
    }

    public ScheduledAction(Action0 action, CompositeSubscription parent) {
        this.action = action;
        this.cancel = new SubscriptionList(new Remover(this, parent));
    }

    public ScheduledAction(Action0 action, SubscriptionList parent) {
        this.action = action;
        this.cancel = new SubscriptionList(new Remover2(this, parent));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } catch (OnErrorNotImplementedException e) {
            signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
        } catch (Throwable e2) {
            signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", e2));
        } finally {
            unsubscribe();
        }
    }

    void signalError(Throwable ie) {
        RxJavaHooks.onError(ie);
        Thread thread = Thread.currentThread();
        thread.getUncaughtExceptionHandler().uncaughtException(thread, ie);
    }

    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    public void unsubscribe() {
        if (!this.cancel.isUnsubscribed()) {
            this.cancel.unsubscribe();
        }
    }

    public void add(Subscription s) {
        this.cancel.add(s);
    }

    public void add(Future<?> f) {
        this.cancel.add(new FutureCompleter(f));
    }

    public void addParent(CompositeSubscription parent) {
        this.cancel.add(new Remover(this, parent));
    }

    public void addParent(SubscriptionList parent) {
        this.cancel.add(new Remover2(this, parent));
    }
}