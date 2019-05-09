// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func0;
import rx.Observable;

public final class OperatorMapNotification<T, R> implements Operator<R, T>
{
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;
    
    public OperatorMapNotification(final Func1<? super T, ? extends R> onNext, final Func1<? super Throwable, ? extends R> onError, final Func0<? extends R> onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final MapNotificationSubscriber mapNotificationSubscriber = new MapNotificationSubscriber((Subscriber<? super R>)subscriber, (Func1<? super T, ? extends R>)this.onNext, (Func1<? super Throwable, ? extends R>)this.onError, (Func0<? extends R>)this.onCompleted);
        subscriber.add(mapNotificationSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                mapNotificationSubscriber.requestInner(n);
            }
        });
        return (Subscriber<? super T>)mapNotificationSubscriber;
    }
    
    static final class MapNotificationSubscriber<T, R> extends Subscriber<T>
    {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final AtomicLong missedRequested;
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        final AtomicReference<Producer> producer;
        final AtomicLong requested;
        R value;
        
        public MapNotificationSubscriber(final Subscriber<? super R> actual, final Func1<? super T, ? extends R> onNext, final Func1<? super Throwable, ? extends R> onError, final Func0<? extends R> onCompleted) {
            this.actual = actual;
            this.onNext = onNext;
            this.onError = onError;
            this.onCompleted = onCompleted;
            this.requested = new AtomicLong();
            this.missedRequested = new AtomicLong();
            this.producer = new AtomicReference<Producer>();
        }
        
        void accountProduced() {
            final long produced = this.produced;
            if (produced != 0L && this.producer.get() != null) {
                BackpressureUtils.produced(this.requested, produced);
            }
        }
        
        @Override
        public void onCompleted() {
            this.accountProduced();
            while (true) {
                try {
                    this.value = (R)this.onCompleted.call();
                    this.tryEmit();
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, this.actual);
                    continue;
                }
                break;
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            this.accountProduced();
            while (true) {
                try {
                    this.value = (R)this.onError.call(t);
                    this.tryEmit();
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this.actual, t);
                    continue;
                }
                break;
            }
        }
        
        @Override
        public void onNext(final T t) {
            try {
                ++this.produced;
                this.actual.onNext((Object)this.onNext.call((Object)t));
            }
            catch (Throwable t2) {
                Exceptions.throwOrReport(t2, this.actual, t);
            }
        }
        
        void requestInner(long andSet) {
            if (andSet < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + andSet);
            }
            if (andSet != 0L) {
                while (true) {
                    final long value = this.requested.get();
                    if ((Long.MIN_VALUE & value) != 0x0L) {
                        final long n = value & Long.MAX_VALUE;
                        if (!this.requested.compareAndSet(value, BackpressureUtils.addCap(n, andSet) | Long.MIN_VALUE)) {
                            continue;
                        }
                        if (n != 0L) {
                            break;
                        }
                        if (!this.actual.isUnsubscribed()) {
                            this.actual.onNext((Object)this.value);
                        }
                        if (!this.actual.isUnsubscribed()) {
                            this.actual.onCompleted();
                            return;
                        }
                        break;
                    }
                    else {
                        if (!this.requested.compareAndSet(value, BackpressureUtils.addCap(value, andSet))) {
                            continue;
                        }
                        final AtomicReference<Producer> producer = this.producer;
                        final Producer producer2 = producer.get();
                        if (producer2 != null) {
                            producer2.request(andSet);
                            return;
                        }
                        BackpressureUtils.getAndAddRequest(this.missedRequested, andSet);
                        final Producer producer3 = producer.get();
                        if (producer3 == null) {
                            break;
                        }
                        andSet = this.missedRequested.getAndSet(0L);
                        if (andSet != 0L) {
                            producer3.request(andSet);
                            return;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public void setProducer(final Producer producer) {
            if (this.producer.compareAndSet(null, producer)) {
                final long andSet = this.missedRequested.getAndSet(0L);
                if (andSet != 0L) {
                    producer.request(andSet);
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }
        
        void tryEmit() {
            long value;
            do {
                value = this.requested.get();
                if ((value & Long.MIN_VALUE) != 0x0L) {
                    return;
                }
            } while (!this.requested.compareAndSet(value, value | Long.MIN_VALUE));
            if (value == 0L && this.producer.get() != null) {
                return;
            }
            if (!this.actual.isUnsubscribed()) {
                this.actual.onNext((Object)this.value);
            }
            if (!this.actual.isUnsubscribed()) {
                this.actual.onCompleted();
            }
        }
    }
}
