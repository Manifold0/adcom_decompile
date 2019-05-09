// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.MissingBackpressureException;
import rx.plugins.RxJavaHooks;
import rx.Subscription;
import rx.Producer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.functions.Action0;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.schedulers.ImmediateScheduler;
import rx.schedulers.Schedulers;
import rx.Subscriber;
import rx.internal.util.RxRingBuffer;
import rx.Scheduler;
import rx.Observable;

public final class OperatorObserveOn<T> implements Operator<T, T>
{
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;
    
    public OperatorObserveOn(final Scheduler scheduler, final boolean b) {
        this(scheduler, b, RxRingBuffer.SIZE);
    }
    
    public OperatorObserveOn(final Scheduler scheduler, final boolean delayError, int size) {
        this.scheduler = scheduler;
        this.delayError = delayError;
        if (size <= 0) {
            size = RxRingBuffer.SIZE;
        }
        this.bufferSize = size;
    }
    
    public static <T> Operator<T, T> rebatch(final int n) {
        return new Operator<T, T>() {
            @Override
            public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
                final ObserveOnSubscriber<Object> observeOnSubscriber = new ObserveOnSubscriber<Object>(Schedulers.immediate(), (Subscriber<? super Object>)subscriber, false, n);
                observeOnSubscriber.init();
                return observeOnSubscriber;
            }
        };
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        if (!(this.scheduler instanceof ImmediateScheduler) && !(this.scheduler instanceof TrampolineScheduler)) {
            final ObserveOnSubscriber<Object> observeOnSubscriber = new ObserveOnSubscriber<Object>(this.scheduler, (Subscriber<? super Object>)subscriber, this.delayError, this.bufferSize);
            observeOnSubscriber.init();
            return observeOnSubscriber;
        }
        return subscriber;
    }
    
    static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super T> child;
        final AtomicLong counter;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final NotificationLite<T> on;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested;
        
        public ObserveOnSubscriber(final Scheduler scheduler, final Subscriber<? super T> child, final boolean delayError, int size) {
            this.requested = new AtomicLong();
            this.counter = new AtomicLong();
            this.child = child;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = delayError;
            this.on = NotificationLite.instance();
            if (size <= 0) {
                size = RxRingBuffer.SIZE;
            }
            this.limit = size - (size >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue<Object>(size);
            }
            else {
                this.queue = new SpscAtomicArrayQueue<Object>(size);
            }
            this.request(size);
        }
        
        @Override
        public void call() {
            long addAndGet = 1L;
            long emitted = this.emitted;
            final Queue<Object> queue = this.queue;
            final Subscriber<? super T> child = this.child;
            final NotificationLite<T> on = this.on;
        Label_0079:
            while (true) {
                long n = this.requested.get();
                while (n != emitted) {
                    final boolean finished = this.finished;
                    final Object poll = queue.poll();
                    final boolean b = poll == null;
                    if (this.checkTerminated(finished, b, child, queue)) {
                        break Label_0079;
                    }
                    if (b) {
                        break;
                    }
                    child.onNext(on.getValue(poll));
                    final long n2 = ++emitted;
                    if (n2 != this.limit) {
                        continue;
                    }
                    n = BackpressureUtils.produced(this.requested, n2);
                    this.request(n2);
                    emitted = 0L;
                }
                if (n == emitted && this.checkTerminated(this.finished, queue.isEmpty(), child, queue)) {
                    break;
                }
                this.emitted = emitted;
                if ((addAndGet = this.counter.addAndGet(-addAndGet)) == 0L) {
                    return;
                }
            }
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super T> subscriber, final Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (!b) {
                return false;
            }
            Label_0074: {
                if (!this.delayError) {
                    break Label_0074;
                }
                if (!b2) {
                    return false;
                }
                final Throwable error = this.error;
                Label_0057: {
                    if (error == null) {
                        break Label_0057;
                    }
                    try {
                        subscriber.onError(error);
                        return false;
                        subscriber.onCompleted();
                        return false;
                    }
                    finally {
                        this.recursiveScheduler.unsubscribe();
                    }
                }
            }
            final Throwable error2 = this.error;
            if (error2 != null) {
                queue.clear();
                try {
                    subscriber.onError(error2);
                    return true;
                }
                finally {
                    this.recursiveScheduler.unsubscribe();
                }
            }
            if (!b2) {
                return false;
            }
            try {
                subscriber.onCompleted();
                return true;
            }
            finally {
                this.recursiveScheduler.unsubscribe();
            }
        }
        
        void init() {
            final Subscriber<? super T> child = this.child;
            child.setProducer(new Producer() {
                @Override
                public void request(final long n) {
                    if (n > 0L) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, n);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            child.add(this.recursiveScheduler);
            child.add(this);
        }
        
        @Override
        public void onCompleted() {
            if (this.isUnsubscribed() || this.finished) {
                return;
            }
            this.finished = true;
            this.schedule();
        }
        
        @Override
        public void onError(final Throwable error) {
            if (this.isUnsubscribed() || this.finished) {
                RxJavaHooks.onError(error);
                return;
            }
            this.error = error;
            this.finished = true;
            this.schedule();
        }
        
        @Override
        public void onNext(final T t) {
            if (this.isUnsubscribed() || this.finished) {
                return;
            }
            if (!this.queue.offer(this.on.next(t))) {
                this.onError(new MissingBackpressureException());
                return;
            }
            this.schedule();
        }
        
        protected void schedule() {
            if (this.counter.getAndIncrement() == 0L) {
                this.recursiveScheduler.schedule(this);
            }
        }
    }
}
