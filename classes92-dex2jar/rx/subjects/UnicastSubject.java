// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import rx.internal.operators.BackpressureUtils;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.Exceptions;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.Subscriber;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Queue;
import rx.internal.operators.NotificationLite;
import rx.Subscription;
import rx.Observer;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;
import rx.functions.Action0;
import rx.Observable;
import rx.annotations.Experimental;

@Experimental
public final class UnicastSubject<T> extends Subject<T, T>
{
    final State<T> state;
    
    private UnicastSubject(final State<T> state) {
        super(state);
        this.state = state;
    }
    
    public static <T> UnicastSubject<T> create() {
        return create(16);
    }
    
    public static <T> UnicastSubject<T> create(final int n) {
        return new UnicastSubject<T>(new State<T>(n, null));
    }
    
    public static <T> UnicastSubject<T> create(final int n, final Action0 action0) {
        return new UnicastSubject<T>(new State<T>(n, action0));
    }
    
    @Override
    public boolean hasObservers() {
        return this.state.subscriber.get() != null;
    }
    
    @Override
    public void onCompleted() {
        this.state.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.state.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.state.onNext(t);
    }
    
    static final class State<T> extends AtomicLong implements Producer, Observer<T>, OnSubscribe<T>, Subscription
    {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final NotificationLite<T> nl;
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber;
        final AtomicReference<Action0> terminateOnce;
        
        public State(final int n, final Action0 action0) {
            this.nl = NotificationLite.instance();
            this.subscriber = new AtomicReference<Subscriber<? super T>>();
            AtomicReference<Action0> terminateOnce;
            if (action0 != null) {
                terminateOnce = new AtomicReference<Action0>(action0);
            }
            else {
                terminateOnce = null;
            }
            this.terminateOnce = terminateOnce;
            Object queue;
            if (n > 1) {
                if (UnsafeAccess.isUnsafeAvailable()) {
                    queue = new SpscUnboundedArrayQueue<Object>(n);
                }
                else {
                    queue = new SpscUnboundedAtomicArrayQueue<Object>(n);
                }
            }
            else if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscLinkedQueue<Object>();
            }
            else {
                queue = new SpscLinkedAtomicQueue<Object>();
            }
            this.queue = (Queue<Object>)queue;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            if (this.subscriber.compareAndSet(null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                return;
            }
            subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super T> subscriber) {
            if (subscriber.isUnsubscribed()) {
                this.queue.clear();
                return true;
            }
            if (b) {
                final Throwable error = this.error;
                if (error != null) {
                    this.queue.clear();
                    subscriber.onError(error);
                    return true;
                }
                if (b2) {
                    subscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }
        
        void doTerminate() {
            final AtomicReference<Action0> terminateOnce = this.terminateOnce;
            if (terminateOnce != null) {
                final Action0 action0 = terminateOnce.get();
                if (action0 != null && terminateOnce.compareAndSet(action0, null)) {
                    action0.call();
                }
            }
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.done;
        }
        
        @Override
        public void onCompleted() {
            int n = 1;
            if (this.done) {
                return;
            }
            this.doTerminate();
            this.done = true;
            Label_0055: {
                if (this.caughtUp) {
                    break Label_0055;
                }
                synchronized (this) {
                    if (this.caughtUp) {
                        n = 0;
                    }
                    // monitorexit(this)
                    if (n != 0) {
                        this.replay();
                        return;
                    }
                }
            }
            this.subscriber.get().onCompleted();
        }
        
        @Override
        public void onError(final Throwable error) {
            int n = 1;
            if (this.done) {
                return;
            }
            this.doTerminate();
            this.error = error;
            this.done = true;
            Label_0060: {
                if (this.caughtUp) {
                    break Label_0060;
                }
                synchronized (this) {
                    if (this.caughtUp) {
                        n = 0;
                    }
                    // monitorexit(this)
                    if (n != 0) {
                        this.replay();
                        return;
                    }
                }
            }
            this.subscriber.get().onError(error);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            Label_0061: {
                if (this.caughtUp) {
                    break Label_0061;
                }
                boolean b = false;
                synchronized (this) {
                    if (!this.caughtUp) {
                        this.queue.offer(this.nl.next(t));
                        b = true;
                    }
                    // monitorexit(this)
                    if (b) {
                        this.replay();
                        return;
                    }
                }
            }
            final Subscriber<? super T> subscriber = this.subscriber.get();
            try {
                subscriber.onNext(t);
            }
            catch (Throwable t2) {
                Exceptions.throwOrReport(t2, subscriber, t);
            }
        }
        
        void replay() {
            while (true) {
                // monitorexit(this)
                Queue<Object> queue = null;
                Subscriber<? super T> subscriber = null;
                int n;
                long value = 0L;
                int n2;
                long n3 = 0L;
                boolean done;
                Object o = null;
                boolean b;
                Label_0086_Outer:Label_0088_Outer:
                while (true) {
                Label_0118_Outer:
                    while (true) {
                    Label_0220:
                        while (true) {
                        Label_0214:
                            while (true) {
                                Label_0209: {
                                    synchronized (this) {
                                        if (this.emitting) {
                                            this.missed = true;
                                            return;
                                        }
                                        this.emitting = true;
                                        // monitorexit(this)
                                        queue = this.queue;
                                        subscriber = this.subscriber.get();
                                        n = 0;
                                        if (subscriber != null) {
                                            if (this.checkTerminated(this.done, queue.isEmpty(), subscriber)) {
                                                break;
                                            }
                                            value = this.get();
                                            if (value != Long.MAX_VALUE) {
                                                break Label_0209;
                                            }
                                            n2 = 1;
                                            n3 = 0L;
                                            if (value != 0L) {
                                                done = this.done;
                                                o = queue.poll();
                                                if (o != null) {
                                                    break Label_0214;
                                                }
                                                b = true;
                                                if (this.checkTerminated(done, b, subscriber)) {
                                                    break;
                                                }
                                                if (!b) {
                                                    break Label_0220;
                                                }
                                            }
                                            if ((n = n2) == 0) {
                                                n = n2;
                                                if (n3 != 0L) {
                                                    this.addAndGet(-n3);
                                                    n = n2;
                                                }
                                            }
                                        }
                                        synchronized (this) {
                                            if (!this.missed) {
                                                if (n != 0 && queue.isEmpty()) {
                                                    this.caughtUp = true;
                                                }
                                                this.emitting = false;
                                                return;
                                            }
                                            break Label_0118_Outer;
                                        }
                                    }
                                }
                                n2 = 0;
                                continue Label_0088_Outer;
                            }
                            b = false;
                            continue;
                        }
                        o = this.nl.getValue(o);
                        try {
                            subscriber.onNext((Object)o);
                            --value;
                            ++n3;
                            continue Label_0118_Outer;
                        }
                        catch (Throwable t) {
                            queue.clear();
                            Exceptions.throwIfFatal(t);
                            subscriber.onError(OnErrorThrowable.addValueAsLastCause(t, o));
                            return;
                        }
                        break;
                    }
                    this.missed = false;
                    continue Label_0086_Outer;
                }
            }
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (n > 0L) {
                BackpressureUtils.getAndAddRequest(this, n);
                this.replay();
            }
            else if (this.done) {
                this.replay();
            }
        }
        
        @Override
        public void unsubscribe() {
            this.doTerminate();
            this.done = true;
            synchronized (this) {
                if (this.emitting) {
                    return;
                }
                this.emitting = true;
                // monitorexit(this)
                this.queue.clear();
            }
        }
    }
}
