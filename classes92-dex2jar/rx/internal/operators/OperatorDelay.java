// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action0;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorDelay<T> implements Operator<T, T>
{
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;
    
    public OperatorDelay(final long delay, final TimeUnit unit, final Scheduler scheduler) {
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        return new Subscriber<T>(subscriber) {
            boolean done;
            
            @Override
            public void onCompleted() {
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        if (!Subscriber.this.done) {
                            Subscriber.this.done = true;
                            subscriber.onCompleted();
                        }
                    }
                }, OperatorDelay.this.delay, OperatorDelay.this.unit);
            }
            
            @Override
            public void onError(final Throwable t) {
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        if (!Subscriber.this.done) {
                            Subscriber.this.done = true;
                            subscriber.onError(t);
                            worker.unsubscribe();
                        }
                    }
                });
            }
            
            @Override
            public void onNext(final T t) {
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        if (!Subscriber.this.done) {
                            subscriber.onNext(t);
                        }
                    }
                }, OperatorDelay.this.delay, OperatorDelay.this.unit);
            }
        };
    }
}
