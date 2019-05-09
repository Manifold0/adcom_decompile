// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorTakeTimed<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    
    public OperatorTakeTimed(final long time, final TimeUnit unit, final Scheduler scheduler) {
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        final TakeSubscriber takeSubscriber = new TakeSubscriber<Object>(new SerializedSubscriber<Object>(subscriber));
        worker.schedule(takeSubscriber, this.time, this.unit);
        return (Subscriber<? super T>)takeSubscriber;
    }
    
    static final class TakeSubscriber<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super T> child;
        
        public TakeSubscriber(final Subscriber<? super T> child) {
            super(child);
            this.child = child;
        }
        
        @Override
        public void call() {
            this.onCompleted();
        }
        
        @Override
        public void onCompleted() {
            this.child.onCompleted();
            this.unsubscribe();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
            this.unsubscribe();
        }
        
        @Override
        public void onNext(final T t) {
            this.child.onNext((Object)t);
        }
    }
}
