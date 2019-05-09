// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import rx.Observable;
import rx.Observer;
import rx.subjects.Subject;

public final class BufferUntilSubscriber<T> extends Subject<T, T>
{
    static final Observer EMPTY_OBSERVER;
    private boolean forward;
    final State<T> state;
    
    static {
        EMPTY_OBSERVER = new Observer() {
            @Override
            public void onCompleted() {
            }
            
            @Override
            public void onError(final Throwable t) {
            }
            
            @Override
            public void onNext(final Object o) {
            }
        };
    }
    
    private BufferUntilSubscriber(final State<T> state) {
        super(new OnSubscribeAction((State)state));
        this.state = state;
    }
    
    public static <T> BufferUntilSubscriber<T> create() {
        return new BufferUntilSubscriber<T>(new State<T>());
    }
    
    private void emit(Object poll) {
        synchronized (this.state.guard) {
            this.state.buffer.add(poll);
            if (this.state.get() != null && !this.state.emitting) {
                this.forward = true;
                this.state.emitting = true;
            }
            // monitorexit(this.state.guard)
            if (this.forward) {
                while (true) {
                    poll = this.state.buffer.poll();
                    if (poll == null) {
                        break;
                    }
                    this.state.nl.accept((Observer<? super T>)this.state.get(), poll);
                }
            }
        }
    }
    
    @Override
    public boolean hasObservers() {
        while (true) {
            synchronized (this.state.guard) {
                if (this.state.get() != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public void onCompleted() {
        if (this.forward) {
            this.state.get().onCompleted();
            return;
        }
        this.emit(this.state.nl.completed());
    }
    
    @Override
    public void onError(final Throwable t) {
        if (this.forward) {
            this.state.get().onError(t);
            return;
        }
        this.emit(this.state.nl.error(t));
    }
    
    @Override
    public void onNext(final T t) {
        if (this.forward) {
            this.state.get().onNext((Object)t);
            return;
        }
        this.emit(this.state.nl.next(t));
    }
    
    static final class OnSubscribeAction<T> implements OnSubscribe<T>
    {
        final State<T> state;
        
        public OnSubscribeAction(final State<T> state) {
            this.state = state;
        }
        
        @Override
        public void call(Subscriber<? super T> o) {
            if (this.state.casObserverRef(null, (Observer<? super T>)o)) {
                while (true) {
                    ((Subscriber)o).add(Subscriptions.create(new Action0() {
                        @Override
                        public void call() {
                            OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
                        }
                    }));
                    boolean b = false;
                    o = this.state.guard;
                    while (true) {
                        synchronized (o) {
                            if (!this.state.emitting) {
                                this.state.emitting = true;
                                b = true;
                            }
                            // monitorexit(o)
                            if (!b) {
                                return;
                            }
                            o = NotificationLite.instance();
                            while (true) {
                                final Object poll = this.state.buffer.poll();
                                if (poll == null) {
                                    break;
                                }
                                ((NotificationLite<Object>)o).accept(this.state.get(), poll);
                            }
                        }
                        synchronized (this.state.guard) {
                            if (this.state.buffer.isEmpty()) {
                                this.state.emitting = false;
                                return;
                            }
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
            ((Observer)o).onError(new IllegalStateException("Only one subscriber allowed!"));
        }
    }
    
    static final class State<T> extends AtomicReference<Observer<? super T>>
    {
        private static final long serialVersionUID = 8026705089538090368L;
        final ConcurrentLinkedQueue<Object> buffer;
        boolean emitting;
        final Object guard;
        final NotificationLite<T> nl;
        
        State() {
            this.guard = new Object();
            this.buffer = new ConcurrentLinkedQueue<Object>();
            this.nl = NotificationLite.instance();
        }
        
        boolean casObserverRef(final Observer<? super T> observer, final Observer<? super T> observer2) {
            return this.compareAndSet(observer, observer2);
        }
    }
}
