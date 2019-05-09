// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.internal.operators.NotificationLite;
import rx.functions.Action1;
import rx.schedulers.TestScheduler;
import rx.Observable;
import rx.Scheduler;

public final class TestSubject<T> extends Subject<T, T>
{
    private final Scheduler.Worker innerScheduler;
    private final SubjectSubscriptionManager<T> state;
    
    protected TestSubject(final OnSubscribe<T> onSubscribe, final SubjectSubscriptionManager<T> state, final TestScheduler testScheduler) {
        super(onSubscribe);
        this.state = state;
        this.innerScheduler = testScheduler.createWorker();
    }
    
    public static <T> TestSubject<T> create(final TestScheduler testScheduler) {
        final SubjectSubscriptionManager<T> subjectSubscriptionManager = (SubjectSubscriptionManager<T>)new SubjectSubscriptionManager<Object>();
        subjectSubscriptionManager.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() {
            @Override
            public void call(final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                subjectObserver.emitFirst(subjectSubscriptionManager.getLatest(), subjectSubscriptionManager.nl);
            }
        };
        subjectSubscriptionManager.onTerminated = subjectSubscriptionManager.onAdded;
        return new TestSubject<T>((OnSubscribe<Object>)subjectSubscriptionManager, (SubjectSubscriptionManager<Object>)subjectSubscriptionManager, testScheduler);
    }
    
    @Override
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
    
    void internalOnCompleted() {
        if (this.state.active) {
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(NotificationLite.instance().completed());
            for (int length = terminate.length, i = 0; i < length; ++i) {
                terminate[i].onCompleted();
            }
        }
    }
    
    void internalOnError(final Throwable t) {
        if (this.state.active) {
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(NotificationLite.instance().error(t));
            for (int length = terminate.length, i = 0; i < length; ++i) {
                terminate[i].onError(t);
            }
        }
    }
    
    void internalOnNext(final T t) {
        final SubjectSubscriptionManager.SubjectObserver<T>[] observers = this.state.observers();
        for (int length = observers.length, i = 0; i < length; ++i) {
            observers[i].onNext(t);
        }
    }
    
    @Override
    public void onCompleted() {
        this.onCompleted(0L);
    }
    
    public void onCompleted(final long n) {
        this.innerScheduler.schedule(new Action0() {
            @Override
            public void call() {
                TestSubject.this.internalOnCompleted();
            }
        }, n, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void onError(final Throwable t) {
        this.onError(t, 0L);
    }
    
    public void onError(final Throwable t, final long n) {
        this.innerScheduler.schedule(new Action0() {
            @Override
            public void call() {
                TestSubject.this.internalOnError(t);
            }
        }, n, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void onNext(final T t) {
        this.onNext(t, 0L);
    }
    
    public void onNext(final T t, final long n) {
        this.innerScheduler.schedule(new Action0() {
            @Override
            public void call() {
                TestSubject.this.internalOnNext(t);
            }
        }, n, TimeUnit.MILLISECONDS);
    }
}
