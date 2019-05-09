// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeDetach<T> implements OnSubscribe<T>
{
    final Observable<T> source;
    
    public OnSubscribeDetach(final Observable<T> source) {
        this.source = source;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final DetachSubscriber detachSubscriber = new DetachSubscriber((Subscriber<? super Object>)subscriber);
        final DetachProducer producer = new DetachProducer((DetachSubscriber)detachSubscriber);
        subscriber.add(producer);
        subscriber.setProducer(producer);
        this.source.unsafeSubscribe((Subscriber<? super T>)detachSubscriber);
    }
    
    static final class DetachProducer<T> implements Producer, Subscription
    {
        final DetachSubscriber<T> parent;
        
        public DetachProducer(final DetachSubscriber<T> parent) {
            this.parent = parent;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.parent.isUnsubscribed();
        }
        
        @Override
        public void request(final long n) {
            this.parent.innerRequest(n);
        }
        
        @Override
        public void unsubscribe() {
            this.parent.innerUnsubscribe();
        }
    }
    
    static final class DetachSubscriber<T> extends Subscriber<T>
    {
        final AtomicReference<Subscriber<? super T>> actual;
        final AtomicReference<Producer> producer;
        final AtomicLong requested;
        
        public DetachSubscriber(final Subscriber<? super T> subscriber) {
            this.actual = new AtomicReference<Subscriber<? super T>>(subscriber);
            this.producer = new AtomicReference<Producer>();
            this.requested = new AtomicLong();
        }
        
        void innerRequest(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            final Producer producer = this.producer.get();
            if (producer != null) {
                producer.request(n);
            }
            else {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                final Producer producer2 = this.producer.get();
                if (producer2 != null && producer2 != TerminatedProducer.INSTANCE) {
                    producer2.request(this.requested.getAndSet(0L));
                }
            }
        }
        
        void innerUnsubscribe() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            this.actual.lazySet(null);
            this.unsubscribe();
        }
        
        @Override
        public void onCompleted() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            final Subscriber<? super T> subscriber = this.actual.getAndSet(null);
            if (subscriber != null) {
                subscriber.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            final Subscriber<? super T> subscriber = this.actual.getAndSet(null);
            if (subscriber != null) {
                subscriber.onError(t);
                return;
            }
            RxJavaHooks.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final Subscriber<? super T> subscriber = this.actual.get();
            if (subscriber != null) {
                subscriber.onNext(t);
            }
        }
        
        @Override
        public void setProducer(final Producer producer) {
            if (this.producer.compareAndSet(null, producer)) {
                producer.request(this.requested.getAndSet(0L));
            }
            else if (this.producer.get() != TerminatedProducer.INSTANCE) {
                throw new IllegalStateException("Producer already set!");
            }
        }
    }
    
    enum TerminatedProducer implements Producer
    {
        INSTANCE;
        
        @Override
        public void request(final long n) {
        }
    }
}
