// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action0;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Producer;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.Subscriber;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorRetryWithPredicate<T> implements Operator<T, Observable<T>>
{
    final Func2<Integer, Throwable, Boolean> predicate;
    
    public OperatorRetryWithPredicate(final Func2<Integer, Throwable, Boolean> predicate) {
        this.predicate = predicate;
    }
    
    @Override
    public Subscriber<? super Observable<T>> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = Schedulers.trampoline().createWorker();
        subscriber.add(worker);
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final ProducerArbiter producer = new ProducerArbiter();
        subscriber.setProducer(producer);
        return (Subscriber<? super Observable<T>>)new SourceSubscriber((Subscriber<? super Object>)subscriber, this.predicate, worker, serialSubscription, producer);
    }
    
    static final class SourceSubscriber<T> extends Subscriber<Observable<T>>
    {
        final AtomicInteger attempts;
        final Subscriber<? super T> child;
        final Scheduler.Worker inner;
        final ProducerArbiter pa;
        final Func2<Integer, Throwable, Boolean> predicate;
        final SerialSubscription serialSubscription;
        
        public SourceSubscriber(final Subscriber<? super T> child, final Func2<Integer, Throwable, Boolean> predicate, final Scheduler.Worker inner, final SerialSubscription serialSubscription, final ProducerArbiter pa) {
            this.attempts = new AtomicInteger();
            this.child = child;
            this.predicate = predicate;
            this.inner = inner;
            this.serialSubscription = serialSubscription;
            this.pa = pa;
        }
        
        @Override
        public void onCompleted() {
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final Observable<T> observable) {
            this.inner.schedule(new Action0() {
                @Override
                public void call() {
                    SourceSubscriber.this.attempts.incrementAndGet();
                    final Subscriber<T> subscriber = new Subscriber<T>() {
                        boolean done;
                        final /* synthetic */ Action0 val$_self;
                        
                        @Override
                        public void onCompleted() {
                            if (!this.done) {
                                this.done = true;
                                SourceSubscriber.this.child.onCompleted();
                            }
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            if (!this.done) {
                                this.done = true;
                                if (!SourceSubscriber.this.predicate.call(SourceSubscriber.this.attempts.get(), t) || SourceSubscriber.this.inner.isUnsubscribed()) {
                                    SourceSubscriber.this.child.onError(t);
                                    return;
                                }
                                SourceSubscriber.this.inner.schedule(this.val$_self);
                            }
                        }
                        
                        @Override
                        public void onNext(final T t) {
                            if (!this.done) {
                                SourceSubscriber.this.child.onNext((Object)t);
                                SourceSubscriber.this.pa.produced(1L);
                            }
                        }
                        
                        @Override
                        public void setProducer(final Producer producer) {
                            SourceSubscriber.this.pa.setProducer(producer);
                        }
                    };
                    SourceSubscriber.this.serialSubscription.set(subscriber);
                    observable.unsafeSubscribe(subscriber);
                }
            });
        }
    }
}
