// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.CompletableSubscriber;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.subjects.PublishSubject;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;
import rx.Completable;
import rx.Observable;
import rx.Observer;
import rx.annotations.Experimental;
import rx.Subscription;
import rx.Scheduler;

@Experimental
public class SchedulerWhen extends Scheduler implements Subscription
{
    static final Subscription SUBSCRIBED;
    static final Subscription UNSUBSCRIBED;
    private final Scheduler actualScheduler;
    private final Subscription subscription;
    private final Observer<Observable<Completable>> workerObserver;
    
    static {
        SUBSCRIBED = new Subscription() {
            @Override
            public boolean isUnsubscribed() {
                return false;
            }
            
            @Override
            public void unsubscribe() {
            }
        };
        UNSUBSCRIBED = Subscriptions.unsubscribed();
    }
    
    public SchedulerWhen(final Func1<Observable<Observable<Completable>>, Completable> func1, final Scheduler actualScheduler) {
        this.actualScheduler = actualScheduler;
        final PublishSubject<Object> create = PublishSubject.create();
        this.workerObserver = new SerializedObserver<Observable<Completable>>((Observer<? super Observable<Completable>>)create);
        this.subscription = func1.call(create.onBackpressureBuffer()).subscribe();
    }
    
    @Override
    public Worker createWorker() {
        final Worker worker = this.actualScheduler.createWorker();
        final BufferUntilSubscriber<Object> create = BufferUntilSubscriber.create();
        final SerializedObserver serializedObserver = new SerializedObserver((Observer<? super Object>)create);
        final Observable<Object> map = create.map((Func1<? super Object, ?>)new Func1<ScheduledAction, Completable>() {
            @Override
            public Completable call(final ScheduledAction scheduledAction) {
                return Completable.create((Completable.OnSubscribe)new Completable.OnSubscribe() {
                    @Override
                    public void call(final CompletableSubscriber completableSubscriber) {
                        completableSubscriber.onSubscribe(scheduledAction);
                        scheduledAction.call(worker);
                        completableSubscriber.onCompleted();
                    }
                });
            }
        });
        final Worker worker2 = new Worker() {
            private final AtomicBoolean unsubscribed = new AtomicBoolean();
            
            @Override
            public boolean isUnsubscribed() {
                return this.unsubscribed.get();
            }
            
            @Override
            public Subscription schedule(final Action0 action0) {
                final ImmediateAction immediateAction = new ImmediateAction(action0);
                serializedObserver.onNext(immediateAction);
                return immediateAction;
            }
            
            @Override
            public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
                final DelayedAction delayedAction = new DelayedAction(action0, n, timeUnit);
                serializedObserver.onNext(delayedAction);
                return delayedAction;
            }
            
            @Override
            public void unsubscribe() {
                if (this.unsubscribed.compareAndSet(false, true)) {
                    worker.unsubscribe();
                    serializedObserver.onCompleted();
                }
            }
        };
        this.workerObserver.onNext((Observable<Completable>)map);
        return worker2;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.subscription.isUnsubscribed();
    }
    
    @Override
    public void unsubscribe() {
        this.subscription.unsubscribe();
    }
    
    private static class DelayedAction extends ScheduledAction
    {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;
        
        public DelayedAction(final Action0 action, final long delayTime, final TimeUnit unit) {
            this.action = action;
            this.delayTime = delayTime;
            this.unit = unit;
        }
        
        @Override
        protected Subscription callActual(final Worker worker) {
            return worker.schedule(this.action, this.delayTime, this.unit);
        }
    }
    
    private static class ImmediateAction extends ScheduledAction
    {
        private final Action0 action;
        
        public ImmediateAction(final Action0 action) {
            this.action = action;
        }
        
        @Override
        protected Subscription callActual(final Worker worker) {
            return worker.schedule(this.action);
        }
    }
    
    private abstract static class ScheduledAction extends AtomicReference<Subscription> implements Subscription
    {
        public ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }
        
        private void call(final Worker worker) {
            final Subscription subscription = this.get();
            if (subscription != SchedulerWhen.UNSUBSCRIBED && subscription == SchedulerWhen.SUBSCRIBED) {
                final Subscription callActual = this.callActual(worker);
                if (!this.compareAndSet(SchedulerWhen.SUBSCRIBED, callActual)) {
                    callActual.unsubscribe();
                }
            }
        }
        
        protected abstract Subscription callActual(final Worker p0);
        
        @Override
        public boolean isUnsubscribed() {
            return this.get().isUnsubscribed();
        }
        
        @Override
        public void unsubscribe() {
            Subscription subscription;
            do {
                subscription = this.get();
                if (subscription == SchedulerWhen.UNSUBSCRIBED) {
                    return;
                }
            } while (!this.compareAndSet(subscription, SchedulerWhen.UNSUBSCRIBED));
            if (subscription != SchedulerWhen.SUBSCRIBED) {
                subscription.unsubscribe();
            }
        }
    }
}
