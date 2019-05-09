// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.exceptions.MissingBackpressureException;
import java.util.concurrent.atomic.AtomicLong;
import rx.Subscriber;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.Queue;
import rx.Producer;
import rx.Subscription;
import rx.Observer;
import rx.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public final class OnSubscribePublishMulticast<T> extends AtomicInteger implements OnSubscribe<T>, Observer<T>, Subscription
{
    static final PublishProducer<?>[] EMPTY;
    static final PublishProducer<?>[] TERMINATED;
    private static final long serialVersionUID = -3741892510772238743L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final ParentSubscriber<T> parent;
    final int prefetch;
    volatile Producer producer;
    final Queue<T> queue;
    volatile PublishProducer<T>[] subscribers;
    
    static {
        EMPTY = new PublishProducer[0];
        TERMINATED = new PublishProducer[0];
    }
    
    public OnSubscribePublishMulticast(final int prefetch, final boolean delayError) {
        if (prefetch <= 0) {
            throw new IllegalArgumentException("prefetch > 0 required but it was " + prefetch);
        }
        this.prefetch = prefetch;
        this.delayError = delayError;
        if (UnsafeAccess.isUnsafeAvailable()) {
            this.queue = new SpscArrayQueue<T>(prefetch);
        }
        else {
            this.queue = new SpscAtomicArrayQueue<T>(prefetch);
        }
        this.subscribers = (PublishProducer<T>[])OnSubscribePublishMulticast.EMPTY;
        this.parent = new ParentSubscriber<T>(this);
    }
    
    boolean add(final PublishProducer<T> publishProducer) {
        if (this.subscribers == OnSubscribePublishMulticast.TERMINATED) {
            return false;
        }
        final PublishProducer<T>[] subscribers;
        synchronized (this) {
            subscribers = this.subscribers;
            if (subscribers == OnSubscribePublishMulticast.TERMINATED) {
                return false;
            }
        }
        final int length = subscribers.length;
        final PublishProducer[] subscribers2 = new PublishProducer[length + 1];
        System.arraycopy(subscribers, 0, subscribers2, 0, length);
        final PublishProducer publishProducer2;
        subscribers2[length] = publishProducer2;
        this.subscribers = (PublishProducer<T>[])subscribers2;
        // monitorexit(this)
        return true;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final PublishProducer<T> producer = new PublishProducer<T>(subscriber, this);
        subscriber.add(producer);
        subscriber.setProducer(producer);
        if (this.add(producer)) {
            if (producer.isUnsubscribed()) {
                this.remove(producer);
                return;
            }
            this.drain();
        }
        else {
            final Throwable error = this.error;
            if (error != null) {
                subscriber.onError(error);
                return;
            }
            subscriber.onCompleted();
        }
    }
    
    boolean checkTerminated(final boolean b, final boolean b2) {
        final boolean b3 = true;
        if (b) {
            if (this.delayError) {
                if (b2) {
                    final PublishProducer<T>[] terminate = this.terminate();
                    final Throwable error = this.error;
                    if (error != null) {
                        final int length = terminate.length;
                        int n = 0;
                        while (true) {
                            final boolean b4 = b3;
                            if (n >= length) {
                                return b4;
                            }
                            terminate[n].actual.onError(error);
                            ++n;
                        }
                    }
                    else {
                        final int length2 = terminate.length;
                        int n2 = 0;
                        while (true) {
                            final boolean b4 = b3;
                            if (n2 >= length2) {
                                return b4;
                            }
                            terminate[n2].actual.onCompleted();
                            ++n2;
                        }
                    }
                }
            }
            else {
                final Throwable error2 = this.error;
                if (error2 != null) {
                    this.queue.clear();
                    final PublishProducer<T>[] terminate2 = this.terminate();
                    final int length3 = terminate2.length;
                    int n3 = 0;
                    while (true) {
                        final boolean b4 = b3;
                        if (n3 >= length3) {
                            return b4;
                        }
                        terminate2[n3].actual.onError(error2);
                        ++n3;
                    }
                }
                else if (b2) {
                    final PublishProducer<T>[] terminate3 = this.terminate();
                    final int length4 = terminate3.length;
                    int n4 = 0;
                    while (true) {
                        final boolean b4 = b3;
                        if (n4 >= length4) {
                            return b4;
                        }
                        terminate3[n4].actual.onCompleted();
                        ++n4;
                    }
                }
            }
        }
        return false;
    }
    
    void drain() {
        if (this.getAndIncrement() == 0) {
            final Queue<T> queue = this.queue;
            int addAndGet = 0;
            do {
                long min = Long.MAX_VALUE;
                final PublishProducer<T>[] subscribers = this.subscribers;
                final int length = subscribers.length;
                for (int length2 = subscribers.length, i = 0; i < length2; ++i) {
                    min = Math.min(min, subscribers[i].get());
                }
                if (length != 0) {
                    long n;
                    for (n = 0L; n != min; ++n) {
                        final boolean done = this.done;
                        final T poll = queue.poll();
                        final boolean b = poll == null;
                        if (this.checkTerminated(done, b)) {
                            return;
                        }
                        if (b) {
                            break;
                        }
                        for (int length3 = subscribers.length, j = 0; j < length3; ++j) {
                            subscribers[j].actual.onNext((Object)poll);
                        }
                    }
                    if (n == min && this.checkTerminated(this.done, queue.isEmpty())) {
                        return;
                    }
                    if (n == 0L) {
                        continue;
                    }
                    final Producer producer = this.producer;
                    if (producer != null) {
                        producer.request(n);
                    }
                    for (int length4 = subscribers.length, k = 0; k < length4; ++k) {
                        BackpressureUtils.produced(subscribers[k], n);
                    }
                }
            } while ((addAndGet = this.addAndGet(-addAndGet)) != 0);
        }
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.parent.isUnsubscribed();
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
        if (!this.queue.offer(t)) {
            this.parent.unsubscribe();
            this.error = new MissingBackpressureException("Queue full?!");
            this.done = true;
        }
        this.drain();
    }
    
    void remove(final PublishProducer<T> publishProducer) {
        PublishProducer<T>[] array = this.subscribers;
        if (array == OnSubscribePublishMulticast.TERMINATED || array == OnSubscribePublishMulticast.EMPTY) {
            return;
        }
        synchronized (this) {
            array = this.subscribers;
            if (array == OnSubscribePublishMulticast.TERMINATED || array == OnSubscribePublishMulticast.EMPTY) {
                return;
            }
        }
        final int n = -1;
        final int length = array.length;
        int n2 = 0;
        // monitorexit(this)
        while (true) {
            Block_6: {
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= length) {
                        break;
                    }
                    final Throwable t;
                    if (array[n2] == t) {
                        break Block_6;
                    }
                    ++n2;
                }
                if (n3 < 0) {
                    // monitorexit(this)
                    return;
                }
                PublishProducer<?>[] subscribers;
                if (length == 1) {
                    subscribers = OnSubscribePublishMulticast.EMPTY;
                }
                else {
                    subscribers = (PublishProducer<?>[])new PublishProducer[length - 1];
                    System.arraycopy(array, 0, subscribers, 0, n3);
                    System.arraycopy(array, n3 + 1, subscribers, n3, length - n3 - 1);
                }
                this.subscribers = (PublishProducer<T>[])subscribers;
                return;
            }
            int n3 = n2;
            continue;
        }
    }
    
    void setProducer(final Producer producer) {
        (this.producer = producer).request(this.prefetch);
    }
    
    public Subscriber<T> subscriber() {
        return this.parent;
    }
    
    PublishProducer<T>[] terminate() {
        final PublishProducer<T>[] subscribers = this.subscribers;
        if (subscribers != OnSubscribePublishMulticast.TERMINATED) {
            synchronized (this) {
                final PublishProducer<T>[] subscribers2 = this.subscribers;
                if (subscribers2 != OnSubscribePublishMulticast.TERMINATED) {
                    this.subscribers = (PublishProducer<T>[])OnSubscribePublishMulticast.TERMINATED;
                }
                return subscribers2;
            }
        }
        return subscribers;
    }
    
    @Override
    public void unsubscribe() {
        this.parent.unsubscribe();
    }
    
    static final class ParentSubscriber<T> extends Subscriber<T>
    {
        final OnSubscribePublishMulticast<T> state;
        
        public ParentSubscriber(final OnSubscribePublishMulticast<T> state) {
            this.state = state;
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
        
        @Override
        public void setProducer(final Producer producer) {
            this.state.setProducer(producer);
        }
    }
    
    static final class PublishProducer<T> extends AtomicLong implements Producer, Subscription
    {
        private static final long serialVersionUID = 960704844171597367L;
        final Subscriber<? super T> actual;
        final AtomicBoolean once;
        final OnSubscribePublishMulticast<T> parent;
        
        public PublishProducer(final Subscriber<? super T> actual, final OnSubscribePublishMulticast<T> parent) {
            this.actual = actual;
            this.parent = parent;
            this.once = new AtomicBoolean();
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.once.get();
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n != 0L) {
                BackpressureUtils.getAndAddRequest(this, n);
                this.parent.drain();
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }
    }
}
