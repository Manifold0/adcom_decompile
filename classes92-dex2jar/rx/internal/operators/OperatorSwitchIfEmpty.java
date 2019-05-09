// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Subscription;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSwitchIfEmpty<T> implements Operator<T, T>
{
    private final Observable<? extends T> alternate;
    
    public OperatorSwitchIfEmpty(final Observable<? extends T> alternate) {
        this.alternate = alternate;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerialSubscription serialSubscription = new SerialSubscription();
        final ProducerArbiter producer = new ProducerArbiter();
        final ParentSubscriber parentSubscriber = new ParentSubscriber<Object>(subscriber, serialSubscription, producer, this.alternate);
        serialSubscription.set(parentSubscriber);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producer);
        return (Subscriber<? super T>)parentSubscriber;
    }
    
    static final class AlternateSubscriber<T> extends Subscriber<T>
    {
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        
        AlternateSubscriber(final Subscriber<? super T> child, final ProducerArbiter arbiter) {
            this.child = child;
            this.arbiter = arbiter;
        }
        
        @Override
        public void onCompleted() {
            this.child.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.child.onNext((Object)t);
            this.arbiter.produced(1L);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.arbiter.setProducer(producer);
        }
    }
    
    static final class ParentSubscriber<T> extends Subscriber<T>
    {
        private final Observable<? extends T> alternate;
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        private boolean empty;
        private final SerialSubscription serial;
        
        ParentSubscriber(final Subscriber<? super T> child, final SerialSubscription serial, final ProducerArbiter arbiter, final Observable<? extends T> alternate) {
            this.empty = true;
            this.child = child;
            this.serial = serial;
            this.arbiter = arbiter;
            this.alternate = alternate;
        }
        
        private void subscribeToAlternate() {
            final AlternateSubscriber<Object> alternateSubscriber = new AlternateSubscriber<Object>((Subscriber<? super Object>)this.child, this.arbiter);
            this.serial.set(alternateSubscriber);
            this.alternate.unsafeSubscribe(alternateSubscriber);
        }
        
        @Override
        public void onCompleted() {
            if (!this.empty) {
                this.child.onCompleted();
            }
            else if (!this.child.isUnsubscribed()) {
                this.subscribeToAlternate();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.empty = false;
            this.child.onNext((Object)t);
            this.arbiter.produced(1L);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.arbiter.setProducer(producer);
        }
    }
}
