// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import rx.Scheduler;
import rx.Observable;

public class OperatorUnsubscribeOn<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    
    public OperatorUnsubscribeOn(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                subscriber.onNext(t);
            }
        };
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                final Scheduler.Worker worker = OperatorUnsubscribeOn.this.scheduler.createWorker();
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        subscriber2.unsubscribe();
                        worker.unsubscribe();
                    }
                });
            }
        }));
        return subscriber2;
    }
}
