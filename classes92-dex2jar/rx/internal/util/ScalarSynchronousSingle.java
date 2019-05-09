// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.functions.Action0;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.Scheduler;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func1;
import rx.SingleSubscriber;
import rx.Single;

public final class ScalarSynchronousSingle<T> extends Single<T>
{
    final T value;
    
    protected ScalarSynchronousSingle(final T value) {
        super((OnSubscribe)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onSuccess((Object)value);
            }
        });
        this.value = value;
    }
    
    public static <T> ScalarSynchronousSingle<T> create(final T t) {
        return new ScalarSynchronousSingle<T>(t);
    }
    
    public T get() {
        return this.value;
    }
    
    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func1) {
        return Single.create((OnSubscribe<R>)new OnSubscribe<R>() {
            @Override
            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                final Single single = func1.call(ScalarSynchronousSingle.this.value);
                if (single instanceof ScalarSynchronousSingle) {
                    singleSubscriber.onSuccess((Object)((ScalarSynchronousSingle)single).value);
                    return;
                }
                final Subscriber<R> subscriber = new Subscriber<R>() {
                    @Override
                    public void onCompleted() {
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        singleSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final R r) {
                        singleSubscriber.onSuccess(r);
                    }
                };
                singleSubscriber.add(subscriber);
                single.unsafeSubscribe((Subscriber<? super Object>)subscriber);
            }
        });
    }
    
    public Single<T> scalarScheduleOn(final Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return Single.create((OnSubscribe<T>)new DirectScheduledEmission((EventLoopsScheduler)scheduler, this.value));
        }
        return Single.create((OnSubscribe<T>)new NormalScheduledEmission(scheduler, this.value));
    }
    
    static final class DirectScheduledEmission<T> implements OnSubscribe<T>
    {
        private final EventLoopsScheduler es;
        private final T value;
        
        DirectScheduledEmission(final EventLoopsScheduler es, final T value) {
            this.es = es;
            this.value = value;
        }
        
        @Override
        public void call(final SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousSingleAction<Object>(singleSubscriber, this.value)));
        }
    }
    
    static final class NormalScheduledEmission<T> implements OnSubscribe<T>
    {
        private final Scheduler scheduler;
        private final T value;
        
        NormalScheduledEmission(final Scheduler scheduler, final T value) {
            this.scheduler = scheduler;
            this.value = value;
        }
        
        @Override
        public void call(final SingleSubscriber<? super T> singleSubscriber) {
            final Scheduler.Worker worker = this.scheduler.createWorker();
            singleSubscriber.add(worker);
            worker.schedule(new ScalarSynchronousSingleAction<Object>(singleSubscriber, this.value));
        }
    }
    
    static final class ScalarSynchronousSingleAction<T> implements Action0
    {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;
        
        ScalarSynchronousSingleAction(final SingleSubscriber<? super T> subscriber, final T value) {
            this.subscriber = subscriber;
            this.value = value;
        }
        
        @Override
        public void call() {
            try {
                this.subscriber.onSuccess((Object)this.value);
            }
            catch (Throwable t) {
                this.subscriber.onError(t);
            }
        }
    }
}
