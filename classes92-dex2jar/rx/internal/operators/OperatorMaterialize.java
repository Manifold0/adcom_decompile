// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Notification;
import rx.Observable;

public final class OperatorMaterialize<T> implements Operator<Notification<T>, T>
{
    OperatorMaterialize() {
    }
    
    public static <T> OperatorMaterialize<T> instance() {
        return (OperatorMaterialize<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Notification<T>> subscriber) {
        final ParentSubscriber<Object> parentSubscriber = new ParentSubscriber<Object>((Subscriber<? super Notification<Object>>)subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                if (n > 0L) {
                    parentSubscriber.requestMore(n);
                }
            }
        });
        return parentSubscriber;
    }
    
    static final class Holder
    {
        static final OperatorMaterialize<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorMaterialize<Object>();
        }
    }
    
    static class ParentSubscriber<T> extends Subscriber<T>
    {
        private boolean busy;
        private final Subscriber<? super Notification<T>> child;
        private boolean missed;
        private final AtomicLong requested;
        private volatile Notification<T> terminalNotification;
        
        ParentSubscriber(final Subscriber<? super Notification<T>> child) {
            this.requested = new AtomicLong();
            this.child = child;
        }
        
        private void decrementRequested() {
            final AtomicLong requested = this.requested;
            long value;
            do {
                value = requested.get();
                if (value == Long.MAX_VALUE) {
                    return;
                }
            } while (!requested.compareAndSet(value, value - 1L));
        }
        
        private void drain() {
            while (true) {
                // monitorexit(this)
                while (true) {
                    synchronized (this) {
                        if (this.busy) {
                            this.missed = true;
                            return;
                        }
                        // monitorexit(this)
                        final AtomicLong requested = this.requested;
                        if (this.child.isUnsubscribed()) {
                            break;
                        }
                        final Notification<T> terminalNotification = this.terminalNotification;
                        if (terminalNotification != null && requested.get() > 0L) {
                            this.terminalNotification = null;
                            this.child.onNext(terminalNotification);
                            if (!this.child.isUnsubscribed()) {
                                this.child.onCompleted();
                                return;
                            }
                            break;
                        }
                    }
                    synchronized (this) {
                        if (!this.missed) {
                            this.busy = false;
                            return;
                        }
                    }
                    continue;
                }
            }
        }
        
        @Override
        public void onCompleted() {
            this.terminalNotification = Notification.createOnCompleted();
            this.drain();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.terminalNotification = Notification.createOnError(t);
            RxJavaHooks.onError(t);
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            this.child.onNext(Notification.createOnNext(t));
            this.decrementRequested();
        }
        
        @Override
        public void onStart() {
            this.request(0L);
        }
        
        void requestMore(final long n) {
            BackpressureUtils.getAndAddRequest(this.requested, n);
            this.request(n);
            this.drain();
        }
    }
}
