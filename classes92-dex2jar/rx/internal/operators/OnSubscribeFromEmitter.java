// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.MissingBackpressureException;
import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import rx.subscriptions.SerialSubscription;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.internal.util.RxRingBuffer;
import rx.Subscriber;
import rx.AsyncEmitter;
import rx.functions.Action1;
import rx.Observable;

public final class OnSubscribeFromEmitter<T> implements OnSubscribe<T>
{
    final Action1<AsyncEmitter<T>> asyncEmitter;
    final AsyncEmitter.BackpressureMode backpressure;
    
    public OnSubscribeFromEmitter(final Action1<AsyncEmitter<T>> asyncEmitter, final AsyncEmitter.BackpressureMode backpressure) {
        this.asyncEmitter = asyncEmitter;
        this.backpressure = backpressure;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        Producer producer = null;
        switch (this.backpressure) {
            default: {
                producer = new BufferAsyncEmitter<T>(subscriber, RxRingBuffer.SIZE);
                break;
            }
            case NONE: {
                producer = new NoneAsyncEmitter<T>(subscriber);
                break;
            }
            case ERROR: {
                producer = new ErrorAsyncEmitter<T>(subscriber);
                break;
            }
            case DROP: {
                producer = new DropAsyncEmitter<T>(subscriber);
                break;
            }
            case LATEST: {
                producer = new LatestAsyncEmitter<T>(subscriber);
                break;
            }
        }
        subscriber.add((Subscription)producer);
        subscriber.setProducer(producer);
        this.asyncEmitter.call((AsyncEmitter<T>)producer);
    }
    
    abstract static class BaseAsyncEmitter<T> extends AtomicLong implements AsyncEmitter<T>, Producer, Subscription
    {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SerialSubscription serial;
        
        public BaseAsyncEmitter(final Subscriber<? super T> actual) {
            this.actual = actual;
            this.serial = new SerialSubscription();
        }
        
        @Override
        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }
        
        @Override
        public void onCompleted() {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onCompleted();
            }
            finally {
                this.serial.unsubscribe();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onError(t);
            }
            finally {
                this.serial.unsubscribe();
            }
        }
        
        void onRequested() {
        }
        
        void onUnsubscribed() {
        }
        
        @Override
        public final void request(final long n) {
            if (BackpressureUtils.validate(n)) {
                BackpressureUtils.getAndAddRequest(this, n);
                this.onRequested();
            }
        }
        
        @Override
        public final long requested() {
            return this.get();
        }
        
        @Override
        public final void setCancellation(final Cancellable cancellable) {
            this.setSubscription(new CancellableSubscription(cancellable));
        }
        
        @Override
        public final void setSubscription(final Subscription subscription) {
            this.serial.set(subscription);
        }
        
        @Override
        public final void unsubscribe() {
            this.serial.unsubscribe();
            this.onUnsubscribed();
        }
    }
    
    static final class BufferAsyncEmitter<T> extends BaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl;
        final Queue<Object> queue;
        final AtomicInteger wip;
        
        public BufferAsyncEmitter(final Subscriber<? super T> subscriber, final int n) {
            super(subscriber);
            Object queue;
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscUnboundedArrayQueue<Object>(n);
            }
            else {
                queue = new SpscUnboundedAtomicArrayQueue<Object>(n);
            }
            this.queue = (Queue<Object>)queue;
            this.wip = new AtomicInteger();
            this.nl = NotificationLite.instance();
        }
        
        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int addAndGet = 1;
            final Subscriber<? super T> actual = this.actual;
            final Queue<Object> queue = this.queue;
            do {
                final long value = this.get();
                long n = 0L;
                while (n != value) {
                    if (actual.isUnsubscribed()) {
                        queue.clear();
                        return;
                    }
                    final boolean done = this.done;
                    final Object poll = queue.poll();
                    boolean b;
                    if (poll == null) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (done && b) {
                        final Throwable error = this.error;
                        if (error != null) {
                            super.onError(error);
                            return;
                        }
                        super.onCompleted();
                        return;
                    }
                    else {
                        if (b) {
                            break;
                        }
                        actual.onNext(this.nl.getValue(poll));
                        ++n;
                    }
                }
                if (n == value) {
                    if (actual.isUnsubscribed()) {
                        queue.clear();
                        return;
                    }
                    final boolean done2 = this.done;
                    final boolean empty = queue.isEmpty();
                    if (done2 && empty) {
                        final Throwable error2 = this.error;
                        if (error2 != null) {
                            super.onError(error2);
                            return;
                        }
                        super.onCompleted();
                        return;
                    }
                }
                if (n != 0L) {
                    BackpressureUtils.produced(this, n);
                }
            } while ((addAndGet = this.wip.addAndGet(-addAndGet)) != 0);
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            this.queue.offer(this.nl.next(t));
            this.drain();
        }
        
        @Override
        void onRequested() {
            this.drain();
        }
        
        @Override
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }
    }
    
    static final class CancellableSubscription extends AtomicReference<AsyncEmitter.Cancellable> implements Subscription
    {
        private static final long serialVersionUID = 5718521705281392066L;
        
        public CancellableSubscription(final AsyncEmitter.Cancellable cancellable) {
            super(cancellable);
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() == null;
        }
        
        @Override
        public void unsubscribe() {
            if (this.get() == null) {
                return;
            }
            final AsyncEmitter.Cancellable cancellable = this.getAndSet(null);
            if (cancellable == null) {
                return;
            }
            try {
                cancellable.cancel();
            }
            catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaHooks.onError(ex);
            }
        }
    }
    
    static final class DropAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 8360058422307496563L;
        
        public DropAsyncEmitter(final Subscriber<? super T> subscriber) {
            super(subscriber);
        }
        
        @Override
        void onOverflow() {
        }
    }
    
    static final class ErrorAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;
        
        public ErrorAsyncEmitter(final Subscriber<? super T> subscriber) {
            super(subscriber);
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            super.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.done = true;
            super.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            super.onNext(t);
        }
        
        @Override
        void onOverflow() {
            this.onError(new MissingBackpressureException("fromEmitter: could not emit value due to lack of requests"));
        }
    }
    
    static final class LatestAsyncEmitter<T> extends BaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl;
        final AtomicReference<Object> queue;
        final AtomicInteger wip;
        
        public LatestAsyncEmitter(final Subscriber<? super T> subscriber) {
            super(subscriber);
            this.queue = new AtomicReference<Object>();
            this.wip = new AtomicInteger();
            this.nl = NotificationLite.instance();
        }
        
        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int addAndGet = 1;
            final Subscriber<? super T> actual = this.actual;
            final AtomicReference<Object> queue = this.queue;
            do {
                final long value = this.get();
                long n = 0L;
                while (n != value) {
                    if (actual.isUnsubscribed()) {
                        queue.lazySet(null);
                        return;
                    }
                    final boolean done = this.done;
                    final Object andSet = queue.getAndSet(null);
                    boolean b;
                    if (andSet == null) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (done && b) {
                        final Throwable error = this.error;
                        if (error != null) {
                            super.onError(error);
                            return;
                        }
                        super.onCompleted();
                        return;
                    }
                    else {
                        if (b) {
                            break;
                        }
                        actual.onNext(this.nl.getValue(andSet));
                        ++n;
                    }
                }
                if (n == value) {
                    if (actual.isUnsubscribed()) {
                        queue.lazySet(null);
                        return;
                    }
                    final boolean done2 = this.done;
                    boolean b2;
                    if (queue.get() == null) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (done2 && b2) {
                        final Throwable error2 = this.error;
                        if (error2 != null) {
                            super.onError(error2);
                            return;
                        }
                        super.onCompleted();
                        return;
                    }
                }
                if (n != 0L) {
                    BackpressureUtils.produced(this, n);
                }
            } while ((addAndGet = this.wip.addAndGet(-addAndGet)) != 0);
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            this.queue.set(this.nl.next(t));
            this.drain();
        }
        
        @Override
        void onRequested() {
            this.drain();
        }
        
        @Override
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }
    }
    
    abstract static class NoOverflowBaseAsyncEmitter<T> extends BaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 4127754106204442833L;
        
        public NoOverflowBaseAsyncEmitter(final Subscriber<? super T> subscriber) {
            super(subscriber);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            if (this.get() != 0L) {
                this.actual.onNext((Object)t);
                BackpressureUtils.produced(this, 1L);
                return;
            }
            this.onOverflow();
        }
        
        abstract void onOverflow();
    }
    
    static final class NoneAsyncEmitter<T> extends BaseAsyncEmitter<T>
    {
        private static final long serialVersionUID = 3776720187248809713L;
        
        public NoneAsyncEmitter(final Subscriber<? super T> subscriber) {
            super(subscriber);
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.actual.isUnsubscribed()) {
                this.actual.onNext((Object)t);
                long value;
                do {
                    value = this.get();
                    if (value != 0L) {
                        continue;
                    }
                } while (!this.compareAndSet(value, value - 1L));
            }
        }
    }
}
