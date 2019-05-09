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
import rx.Observable;

public final class OperatorTakeLast<T> implements Operator<T, T>
{
    final int count;
    
    public OperatorTakeLast(final int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count cannot be negative");
        }
        this.count = count;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final TakeLastSubscriber takeLastSubscriber = (TakeLastSubscriber)new TakeLastSubscriber<Object>((Subscriber<? super T>)subscriber, this.count);
        subscriber.add(takeLastSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                takeLastSubscriber.requestMore(n);
            }
        });
        return (Subscriber<? super T>)takeLastSubscriber;
    }
    
    static final class TakeLastSubscriber<T> extends Subscriber<T> implements Func1<Object, T>
    {
        final Subscriber<? super T> actual;
        final int count;
        final NotificationLite<T> nl;
        final ArrayDeque<Object> queue;
        final AtomicLong requested;
        
        public TakeLastSubscriber(final Subscriber<? super T> actual, final int count) {
            this.actual = actual;
            this.count = count;
            this.requested = new AtomicLong();
            this.queue = new ArrayDeque<Object>();
            this.nl = NotificationLite.instance();
        }
        
        @Override
        public T call(final Object o) {
            return this.nl.getValue(o);
        }
        
        @Override
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.requested, (Queue<Object>)this.queue, (Subscriber<? super Object>)this.actual, (Func1<? super Object, ?>)this);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.queue.clear();
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.queue.size() == this.count) {
                this.queue.poll();
            }
            this.queue.offer(this.nl.next(t));
        }
        
        void requestMore(final long n) {
            if (n > 0L) {
                BackpressureUtils.postCompleteRequest(this.requested, n, (Queue<Object>)this.queue, (Subscriber<? super Object>)this.actual, (Func1<? super Object, ?>)this);
            }
        }
    }
}
