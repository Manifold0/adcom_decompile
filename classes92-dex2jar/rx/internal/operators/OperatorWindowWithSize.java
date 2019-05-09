// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Iterator;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import rx.subjects.UnicastSubject;
import rx.Producer;
import rx.subscriptions.Subscriptions;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subjects.Subject;
import rx.Subscription;
import rx.functions.Action0;
import rx.Subscriber;
import rx.Observable;

public final class OperatorWindowWithSize<T> implements Operator<Observable<T>, T>
{
    final int size;
    final int skip;
    
    public OperatorWindowWithSize(final int size, final int skip) {
        this.size = size;
        this.skip = skip;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Observable<T>> subscriber) {
        if (this.skip == this.size) {
            final WindowExact<Object> windowExact = new WindowExact<Object>((Subscriber<? super Observable<Object>>)subscriber, this.size);
            subscriber.add(windowExact.cancel);
            subscriber.setProducer(windowExact.createProducer());
            return windowExact;
        }
        if (this.skip > this.size) {
            final WindowSkip<Object> windowSkip = new WindowSkip<Object>((Subscriber<? super Observable<Object>>)subscriber, this.size, this.skip);
            subscriber.add(windowSkip.cancel);
            subscriber.setProducer(windowSkip.createProducer());
            return windowSkip;
        }
        final WindowOverlap<Object> windowOverlap = new WindowOverlap<Object>((Subscriber<? super Observable<Object>>)subscriber, this.size, this.skip);
        subscriber.add(windowOverlap.cancel);
        subscriber.setProducer(windowOverlap.createProducer());
        return windowOverlap;
    }
    
    static final class WindowExact<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        int index;
        final int size;
        Subject<T, T> window;
        final AtomicInteger wip;
        
        public WindowExact(final Subscriber<? super Observable<T>> actual, final int size) {
            this.actual = actual;
            this.size = size;
            this.wip = new AtomicInteger(1);
            this.add(this.cancel = Subscriptions.create(this));
            this.request(0L);
        }
        
        @Override
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                this.unsubscribe();
            }
        }
        
        Producer createProducer() {
            return new Producer() {
                @Override
                public void request(long multiplyCap) {
                    if (multiplyCap < 0L) {
                        throw new IllegalArgumentException("n >= 0 required but it was " + multiplyCap);
                    }
                    if (multiplyCap != 0L) {
                        multiplyCap = BackpressureUtils.multiplyCap(WindowExact.this.size, multiplyCap);
                        Subscriber.this.request(multiplyCap);
                    }
                }
            };
        }
        
        @Override
        public void onCompleted() {
            final Subject<T, T> window = this.window;
            if (window != null) {
                this.window = null;
                window.onCompleted();
            }
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            final Subject<T, T> window = this.window;
            if (window != null) {
                this.window = null;
                window.onError(t);
            }
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final int index = this.index;
            Observable<R> window = this.window;
            if (index == 0) {
                this.wip.getAndIncrement();
                window = UnicastSubject.create(this.size, this);
                this.window = (Subject<T, T>)window;
                this.actual.onNext((Object)window);
            }
            final int index2 = index + 1;
            ((Observer<T>)window).onNext(t);
            if (index2 == this.size) {
                this.index = 0;
                this.window = null;
                ((Observer)window).onCompleted();
                return;
            }
            this.index = index2;
        }
    }
    
    static final class WindowOverlap<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        volatile boolean done;
        final AtomicInteger drainWip;
        Throwable error;
        int index;
        int produced;
        final Queue<Subject<T, T>> queue;
        final AtomicLong requested;
        final int size;
        final int skip;
        final ArrayDeque<Subject<T, T>> windows;
        final AtomicInteger wip;
        
        public WindowOverlap(final Subscriber<? super Observable<T>> actual, final int size, final int skip) {
            this.actual = actual;
            this.size = size;
            this.skip = skip;
            this.wip = new AtomicInteger(1);
            this.windows = new ArrayDeque<Subject<T, T>>();
            this.drainWip = new AtomicInteger();
            this.requested = new AtomicLong();
            this.add(this.cancel = Subscriptions.create(this));
            this.request(0L);
            this.queue = new SpscLinkedArrayQueue<Subject<T, T>>((skip - 1 + size) / skip);
        }
        
        @Override
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                this.unsubscribe();
            }
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super Subject<T, T>> subscriber, final Queue<Subject<T, T>> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (b) {
                final Throwable error = this.error;
                if (error != null) {
                    queue.clear();
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
        
        Producer createProducer() {
            return new WindowOverlapProducer();
        }
        
        void drain() {
            final AtomicInteger drainWip = this.drainWip;
            if (drainWip.getAndIncrement() == 0) {
                final Subscriber<? super Observable<T>> actual = this.actual;
                final Queue<Subject<T, T>> queue = this.queue;
                int addAndGet = 1;
                do {
                    long value;
                    long n;
                    for (value = this.requested.get(), n = 0L; n != value; ++n) {
                        final boolean done = this.done;
                        final Subject<T, T> subject = queue.poll();
                        final boolean b = subject == null;
                        if (this.checkTerminated(done, b, (Subscriber<? super Subject<T, T>>)actual, queue)) {
                            return;
                        }
                        if (b) {
                            break;
                        }
                        actual.onNext(subject);
                    }
                    if (n == value && this.checkTerminated(this.done, queue.isEmpty(), (Subscriber<? super Subject<T, T>>)actual, queue)) {
                        return;
                    }
                    if (n != 0L && value != Long.MAX_VALUE) {
                        this.requested.addAndGet(-n);
                    }
                } while ((addAndGet = drainWip.addAndGet(-addAndGet)) != 0);
            }
        }
        
        @Override
        public void onCompleted() {
            final Iterator<Subject<T, T>> iterator = this.windows.iterator();
            while (iterator.hasNext()) {
                iterator.next().onCompleted();
            }
            this.windows.clear();
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            final Iterator<Subject<T, T>> iterator = this.windows.iterator();
            while (iterator.hasNext()) {
                iterator.next().onError(error);
            }
            this.windows.clear();
            this.error = error;
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            final int index = this.index;
            final ArrayDeque<Subject<T, T>> windows = this.windows;
            if (index == 0 && !this.actual.isUnsubscribed()) {
                this.wip.getAndIncrement();
                final UnicastSubject<Object> create = UnicastSubject.create(16, this);
                windows.offer((Subject<T, T>)create);
                this.queue.offer((Subject<T, T>)create);
                this.drain();
            }
            final Iterator<Subject<T, T>> iterator = this.windows.iterator();
            while (iterator.hasNext()) {
                iterator.next().onNext(t);
            }
            final int produced = this.produced + 1;
            if (produced == this.size) {
                this.produced = produced - this.skip;
                final UnicastSubject unicastSubject = (UnicastSubject)windows.poll();
                if (unicastSubject != null) {
                    unicastSubject.onCompleted();
                }
            }
            else {
                this.produced = produced;
            }
            final int index2 = index + 1;
            if (index2 == this.skip) {
                this.index = 0;
                return;
            }
            this.index = index2;
        }
        
        final class WindowOverlapProducer extends AtomicBoolean implements Producer
        {
            private static final long serialVersionUID = 4625807964358024108L;
            
            @Override
            public void request(final long n) {
                if (n < 0L) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                }
                if (n != 0L) {
                    final WindowOverlap this$0 = WindowOverlap.this;
                    if (!this.get() && this.compareAndSet(false, true)) {
                        this$0.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(this$0.skip, n - 1L), this$0.size));
                    }
                    else {
                        Subscriber.this.request(BackpressureUtils.multiplyCap(this$0.skip, n));
                    }
                    BackpressureUtils.getAndAddRequest(this$0.requested, n);
                    this$0.drain();
                }
            }
        }
    }
    
    static final class WindowSkip<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        int index;
        final int size;
        final int skip;
        Subject<T, T> window;
        final AtomicInteger wip;
        
        public WindowSkip(final Subscriber<? super Observable<T>> actual, final int size, final int skip) {
            this.actual = actual;
            this.size = size;
            this.skip = skip;
            this.wip = new AtomicInteger(1);
            this.add(this.cancel = Subscriptions.create(this));
            this.request(0L);
        }
        
        @Override
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                this.unsubscribe();
            }
        }
        
        Producer createProducer() {
            return new WindowSkipProducer();
        }
        
        @Override
        public void onCompleted() {
            final Subject<T, T> window = this.window;
            if (window != null) {
                this.window = null;
                window.onCompleted();
            }
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            final Subject<T, T> window = this.window;
            if (window != null) {
                this.window = null;
                window.onError(t);
            }
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final int index = this.index;
            Observable<R> window = this.window;
            if (index == 0) {
                this.wip.getAndIncrement();
                window = UnicastSubject.create(this.size, this);
                this.window = (Subject<T, T>)window;
                this.actual.onNext((Object)window);
            }
            final int n = index + 1;
            if (window != null) {
                ((Observer<T>)window).onNext(t);
            }
            if (n == this.size) {
                this.index = n;
                this.window = null;
                ((Observer)window).onCompleted();
                return;
            }
            if (n == this.skip) {
                this.index = 0;
                return;
            }
            this.index = n;
        }
        
        final class WindowSkipProducer extends AtomicBoolean implements Producer
        {
            private static final long serialVersionUID = 4625807964358024108L;
            
            @Override
            public void request(final long n) {
                if (n < 0L) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                }
                if (n != 0L) {
                    final WindowSkip this$0 = WindowSkip.this;
                    if (this.get() || !this.compareAndSet(false, true)) {
                        this$0.request(BackpressureUtils.multiplyCap(n, this$0.skip));
                        return;
                    }
                    this$0.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(n, this$0.size), BackpressureUtils.multiplyCap(this$0.skip - this$0.size, n - 1L)));
                }
            }
        }
    }
}
