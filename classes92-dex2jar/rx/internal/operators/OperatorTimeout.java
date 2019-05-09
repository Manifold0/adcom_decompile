// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.functions.Action0;
import rx.Subscription;
import rx.Scheduler;
import rx.Observable;
import java.util.concurrent.TimeUnit;

public final class OperatorTimeout<T> extends OperatorTimeoutBase<T>
{
    public OperatorTimeout(final long n, final TimeUnit timeUnit, final Observable<? extends T> observable, final Scheduler scheduler) {
        super((FirstTimeoutStub)new FirstTimeoutStub<T>() {
            @Override
            public Subscription call(final TimeoutSubscriber<T> timeoutSubscriber, final Long n, final Scheduler.Worker worker) {
                return worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        timeoutSubscriber.onTimeout(n);
                    }
                }, n, timeUnit);
            }
        }, (TimeoutStub)new TimeoutStub<T>() {
            @Override
            public Subscription call(final TimeoutSubscriber<T> timeoutSubscriber, final Long n, final T t, final Scheduler.Worker worker) {
                return worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        timeoutSubscriber.onTimeout(n);
                    }
                }, n, timeUnit);
            }
        }, observable, scheduler);
    }
}
