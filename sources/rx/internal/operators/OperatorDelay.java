package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorDelay<T> implements Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OperatorDelay(long delay, TimeUnit unit, Scheduler scheduler) {
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        final Worker worker = this.scheduler.createWorker();
        child.add(worker);
        return new Subscriber<T>(child) {
            boolean done;

            /* renamed from: rx.internal.operators.OperatorDelay$1$1 */
            class C31611 implements Action0 {
                C31611() {
                }

                public void call() {
                    if (!C31641.this.done) {
                        C31641.this.done = true;
                        child.onCompleted();
                    }
                }
            }

            public void onCompleted() {
                worker.schedule(new C31611(), OperatorDelay.this.delay, OperatorDelay.this.unit);
            }

            public void onError(final Throwable e) {
                worker.schedule(new Action0() {
                    public void call() {
                        if (!C31641.this.done) {
                            C31641.this.done = true;
                            child.onError(e);
                            worker.unsubscribe();
                        }
                    }
                });
            }

            public void onNext(final T t) {
                worker.schedule(new Action0() {
                    public void call() {
                        if (!C31641.this.done) {
                            child.onNext(t);
                        }
                    }
                }, OperatorDelay.this.delay, OperatorDelay.this.unit);
            }
        };
    }
}
