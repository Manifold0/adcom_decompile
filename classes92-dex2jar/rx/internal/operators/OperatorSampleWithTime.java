// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicReference;
import rx.functions.Action0;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorSampleWithTime<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    
    public OperatorSampleWithTime(final long time, final TimeUnit unit, final Scheduler scheduler) {
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber<Object> serializedSubscriber = new SerializedSubscriber<Object>((Subscriber<? super Object>)subscriber);
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        final SamplerSubscriber samplerSubscriber = new SamplerSubscriber<Object>(serializedSubscriber);
        subscriber.add(samplerSubscriber);
        worker.schedulePeriodically(samplerSubscriber, this.time, this.time, this.unit);
        return (Subscriber<? super T>)samplerSubscriber;
    }
    
    static final class SamplerSubscriber<T> extends Subscriber<T> implements Action0
    {
        private static final Object EMPTY_TOKEN;
        private final Subscriber<? super T> subscriber;
        final AtomicReference<Object> value;
        
        static {
            EMPTY_TOKEN = new Object();
        }
        
        public SamplerSubscriber(final Subscriber<? super T> subscriber) {
            this.value = new AtomicReference<Object>(SamplerSubscriber.EMPTY_TOKEN);
            this.subscriber = subscriber;
        }
        
        private void emitIfNonEmpty() {
            final Object andSet = this.value.getAndSet(SamplerSubscriber.EMPTY_TOKEN);
            if (andSet == SamplerSubscriber.EMPTY_TOKEN) {
                return;
            }
            try {
                this.subscriber.onNext((Object)andSet);
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, this);
            }
        }
        
        @Override
        public void call() {
            this.emitIfNonEmpty();
        }
        
        @Override
        public void onCompleted() {
            this.emitIfNonEmpty();
            this.subscriber.onCompleted();
            this.unsubscribe();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.subscriber.onError(t);
            this.unsubscribe();
        }
        
        @Override
        public void onNext(final T t) {
            this.value.set(t);
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
    }
}
