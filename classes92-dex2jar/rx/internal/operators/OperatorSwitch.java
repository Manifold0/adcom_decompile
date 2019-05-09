// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import java.util.ArrayList;
import rx.exceptions.CompositeException;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.subscriptions.SerialSubscription;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSwitch<T> implements Operator<T, Observable<? extends T>>
{
    final boolean delayError;
    
    OperatorSwitch(final boolean delayError) {
        this.delayError = delayError;
    }
    
    public static <T> OperatorSwitch<T> instance(final boolean b) {
        if (b) {
            return (OperatorSwitch<T>)HolderDelayError.INSTANCE;
        }
        return (OperatorSwitch<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super Observable<? extends T>> call(final Subscriber<? super T> subscriber) {
        final SwitchSubscriber switchSubscriber = new SwitchSubscriber(subscriber, this.delayError);
        subscriber.add(switchSubscriber);
        switchSubscriber.init();
        return (Subscriber<? super Observable<? extends T>>)switchSubscriber;
    }
    
    static final class Holder
    {
        static final OperatorSwitch<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorSwitch<Object>(false);
        }
    }
    
    static final class HolderDelayError
    {
        static final OperatorSwitch<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorSwitch<Object>(true);
        }
    }
    
    static final class InnerSubscriber<T> extends Subscriber<T>
    {
        private final long id;
        private final SwitchSubscriber<T> parent;
        
        InnerSubscriber(final long id, final SwitchSubscriber<T> parent) {
            this.id = id;
            this.parent = parent;
        }
        
        @Override
        public void onCompleted() {
            this.parent.complete(this.id);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.parent.error(t, this.id);
        }
        
        @Override
        public void onNext(final T t) {
            this.parent.emit(t, this);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.parent.innerProducer(producer, this.id);
        }
    }
    
    static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>>
    {
        static final Throwable TERMINAL_ERROR;
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        final AtomicLong index;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        final NotificationLite<T> nl;
        Producer producer;
        final SpscLinkedArrayQueue<Object> queue;
        long requested;
        final SerialSubscription serial;
        
        static {
            TERMINAL_ERROR = new Throwable("Terminal error");
        }
        
        SwitchSubscriber(final Subscriber<? super T> child, final boolean delayError) {
            this.child = child;
            this.serial = new SerialSubscription();
            this.delayError = delayError;
            this.index = new AtomicLong();
            this.queue = new SpscLinkedArrayQueue<Object>(RxRingBuffer.SIZE);
            this.nl = NotificationLite.instance();
        }
        
        protected boolean checkTerminated(final boolean b, final boolean b2, final Throwable t, final SpscLinkedArrayQueue<Object> spscLinkedArrayQueue, final Subscriber<? super T> subscriber, final boolean b3) {
            if (this.delayError) {
                if (b && !b2 && b3) {
                    if (t != null) {
                        subscriber.onError(t);
                        return true;
                    }
                    subscriber.onCompleted();
                    return true;
                }
            }
            else {
                if (t != null) {
                    spscLinkedArrayQueue.clear();
                    subscriber.onError(t);
                    return true;
                }
                if (b && !b2 && b3) {
                    subscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }
        
        void childRequested(final long n) {
            synchronized (this) {
                final Producer producer = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, n);
                // monitorexit(this)
                if (producer != null) {
                    producer.request(n);
                }
                this.drain();
            }
        }
        
        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }
        
        void complete(final long n) {
            synchronized (this) {
                if (this.index.get() != n) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                // monitorexit(this)
                this.drain();
            }
        }
        
        void drain() {
            while (true) {
                // monitorexit(this)
            Label_0094_Outer:
                while (true) {
                Label_0292:
                    while (true) {
                        final SpscLinkedArrayQueue<Object> queue;
                        final AtomicLong index;
                        final Subscriber<? super T> child;
                        long n = 0L;
                        Label_0238: {
                            synchronized (this) {
                                if (this.emitting) {
                                    this.missed = true;
                                    return;
                                }
                                this.emitting = true;
                                final boolean b = this.innerActive;
                                long requested = this.requested;
                                final Throwable t = this.error;
                                if (t != null && t != SwitchSubscriber.TERMINAL_ERROR && !this.delayError) {
                                    this.error = SwitchSubscriber.TERMINAL_ERROR;
                                }
                                // monitorexit(this)
                                queue = this.queue;
                                index = this.index;
                                child = this.child;
                                final boolean b2 = this.mainDone;
                                n = 0L;
                                if (n != requested) {
                                    if (child.isUnsubscribed()) {
                                        break;
                                    }
                                    final boolean empty = queue.isEmpty();
                                    if (this.checkTerminated(b2, b, t, queue, child, empty)) {
                                        break;
                                    }
                                    if (!empty) {
                                        break Label_0238;
                                    }
                                }
                                if (n == requested && (child.isUnsubscribed() || this.checkTerminated(this.mainDone, b, t, queue, child, queue.isEmpty()))) {
                                    break;
                                }
                                synchronized (this) {
                                    final long n2 = requested = this.requested;
                                    if (n2 != Long.MAX_VALUE) {
                                        requested = n2 - n;
                                        this.requested = requested;
                                    }
                                    if (!this.missed) {
                                        this.emitting = false;
                                        return;
                                    }
                                    break Label_0292;
                                }
                            }
                        }
                        final InnerSubscriber<Object> innerSubscriber = queue.poll();
                        final T value = this.nl.getValue(queue.poll());
                        if (index.get() == innerSubscriber.id) {
                            child.onNext(value);
                            ++n;
                            continue;
                        }
                        continue;
                    }
                    this.missed = false;
                    final boolean b2 = this.mainDone;
                    final boolean b = this.innerActive;
                    final Throwable t = this.error;
                    if (t != null && t != SwitchSubscriber.TERMINAL_ERROR && !this.delayError) {
                        this.error = SwitchSubscriber.TERMINAL_ERROR;
                    }
                    continue Label_0094_Outer;
                }
            }
        }
        
        void emit(final T t, final InnerSubscriber<T> innerSubscriber) {
            synchronized (this) {
                if (this.index.get() != ((InnerSubscriber<Object>)innerSubscriber).id) {
                    return;
                }
                this.queue.offer(innerSubscriber, this.nl.next(t));
                // monitorexit(this)
                this.drain();
            }
        }
        
        void error(final Throwable t, final long n) {
            synchronized (this) {
                int updateError;
                if (this.index.get() == n) {
                    updateError = (this.updateError(t) ? 1 : 0);
                    this.innerActive = false;
                    this.producer = null;
                }
                else {
                    updateError = 1;
                }
                // monitorexit(this)
                if (updateError != 0) {
                    this.drain();
                    return;
                }
            }
            this.pluginError(t);
        }
        
        void init() {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() {
                @Override
                public void request(final long n) {
                    if (n > 0L) {
                        SwitchSubscriber.this.childRequested(n);
                    }
                    else if (n < 0L) {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + n);
                    }
                }
            });
        }
        
        void innerProducer(final Producer producer, long requested) {
            synchronized (this) {
                if (this.index.get() != requested) {
                    return;
                }
                requested = this.requested;
                this.producer = producer;
                // monitorexit(this)
                producer.request(requested);
            }
        }
        
        @Override
        public void onCompleted() {
            this.mainDone = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this) {
                final boolean updateError = this.updateError(t);
                // monitorexit(this)
                if (updateError) {
                    this.mainDone = true;
                    this.drain();
                    return;
                }
            }
            this.pluginError(t);
        }
        
        @Override
        public void onNext(final Observable<? extends T> observable) {
            final long incrementAndGet = this.index.incrementAndGet();
            final Subscription value = this.serial.get();
            if (value != null) {
                value.unsubscribe();
            }
            synchronized (this) {
                final InnerSubscriber innerSubscriber = new InnerSubscriber<Object>(incrementAndGet, (SwitchSubscriber)this);
                this.innerActive = true;
                this.producer = null;
                // monitorexit(this)
                this.serial.set(innerSubscriber);
                observable.unsafeSubscribe((Subscriber<? super T>)innerSubscriber);
            }
        }
        
        void pluginError(final Throwable t) {
            RxJavaHooks.onError(t);
        }
        
        boolean updateError(final Throwable error) {
            final Throwable error2 = this.error;
            if (error2 == SwitchSubscriber.TERMINAL_ERROR) {
                return false;
            }
            if (error2 == null) {
                this.error = error;
            }
            else if (error2 instanceof CompositeException) {
                final ArrayList list = new ArrayList<Throwable>(((CompositeException)error2).getExceptions());
                list.add(error);
                this.error = new CompositeException((Collection<? extends Throwable>)list);
            }
            else {
                this.error = new CompositeException(new Throwable[] { error2, error });
            }
            return true;
        }
    }
}
