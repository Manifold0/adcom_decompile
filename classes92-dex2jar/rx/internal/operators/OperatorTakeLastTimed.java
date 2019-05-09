// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayDeque;
import rx.functions.Func1;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorTakeLastTimed<T> implements Operator<T, T>
{
    final long ageMillis;
    final int count;
    final Scheduler scheduler;
    
    public OperatorTakeLastTimed(final int count, final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.ageMillis = timeUnit.toMillis(n);
        this.scheduler = scheduler;
        this.count = count;
    }
    
    public OperatorTakeLastTimed(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        this.ageMillis = timeUnit.toMillis(n);
        this.scheduler = scheduler;
        this.count = -1;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final TakeLastTimedSubscriber takeLastTimedSubscriber = (TakeLastTimedSubscriber)new TakeLastTimedSubscriber<Object>((Subscriber<? super T>)subscriber, this.count, this.ageMillis, this.scheduler);
        subscriber.add(takeLastTimedSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                takeLastTimedSubscriber.requestMore(n);
            }
        });
        return (Subscriber<? super T>)takeLastTimedSubscriber;
    }
    
    static final class TakeLastTimedSubscriber<T> extends Subscriber<T> implements Func1<Object, T>
    {
        final Subscriber<? super T> actual;
        final long ageMillis;
        final int count;
        final NotificationLite<T> nl;
        final ArrayDeque<Object> queue;
        final ArrayDeque<Long> queueTimes;
        final AtomicLong requested;
        final Scheduler scheduler;
        
        public TakeLastTimedSubscriber(final Subscriber<? super T> actual, final int count, final long ageMillis, final Scheduler scheduler) {
            this.actual = actual;
            this.count = count;
            this.ageMillis = ageMillis;
            this.scheduler = scheduler;
            this.requested = new AtomicLong();
            this.queue = new ArrayDeque<Object>();
            this.queueTimes = new ArrayDeque<Long>();
            this.nl = NotificationLite.instance();
        }
        
        @Override
        public T call(final Object o) {
            return this.nl.getValue(o);
        }
        
        protected void evictOld(final long n) {
            final long ageMillis = this.ageMillis;
            while (true) {
                final Long n2 = this.queueTimes.peek();
                if (n2 == null || n2 >= n - ageMillis) {
                    break;
                }
                this.queue.poll();
                this.queueTimes.poll();
            }
        }
        
        @Override
        public void onCompleted() {
            this.evictOld(this.scheduler.now());
            this.queueTimes.clear();
            BackpressureUtils.postCompleteDone(this.requested, (Queue<Object>)this.queue, (Subscriber<? super Object>)this.actual, (Func1<? super Object, ?>)this);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.queue.clear();
            this.queueTimes.clear();
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.count != 0) {
                final long now = this.scheduler.now();
                if (this.queue.size() == this.count) {
                    this.queue.poll();
                    this.queueTimes.poll();
                }
                this.evictOld(now);
                this.queue.offer(this.nl.next(t));
                this.queueTimes.offer(now);
            }
        }
        
        void requestMore(final long n) {
            BackpressureUtils.postCompleteRequest(this.requested, n, (Queue<Object>)this.queue, (Subscriber<? super Object>)this.actual, (Func1<? super Object, ?>)this);
        }
    }
}
