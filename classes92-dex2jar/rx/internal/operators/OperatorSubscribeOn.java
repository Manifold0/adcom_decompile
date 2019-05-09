// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.functions.Action0;
import rx.Subscription;
import rx.Subscriber;
import rx.Scheduler;
import rx.Observable;

public final class OperatorSubscribeOn<T> implements OnSubscribe<T>
{
    final Scheduler scheduler;
    final Observable<T> source;
    
    public OperatorSubscribeOn(final Observable<T> source, final Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = source;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        worker.schedule(new Action0() {
            @Override
            public void call() {
                OperatorSubscribeOn.this.source.unsafeSubscribe(new Subscriber<T>(subscriber) {
                    final /* synthetic */ Thread val$t = Thread.currentThread();
                    
                    @Override
                    public void onCompleted() {
                        try {
                            subscriber.onCompleted();
                        }
                        finally {
                            worker.unsubscribe();
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        try {
                            subscriber.onError(t);
                        }
                        finally {
                            worker.unsubscribe();
                        }
                    }
                    
                    @Override
                    public void onNext(final T t) {
                        subscriber.onNext(t);
                    }
                    
                    @Override
                    public void setProducer(final Producer producer) {
                        subscriber.setProducer(new Producer() {
                            @Override
                            public void request(final long n) {
                                if (Subscriber.this.val$t == Thread.currentThread()) {
                                    producer.request(n);
                                    return;
                                }
                                worker.schedule(new Action0() {
                                    @Override
                                    public void call() {
                                        producer.request(n);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}
