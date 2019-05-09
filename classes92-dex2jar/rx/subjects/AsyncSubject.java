// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.util.List;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import rx.Producer;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;
import rx.functions.Action1;
import rx.Observable;
import rx.internal.operators.NotificationLite;

public final class AsyncSubject<T> extends Subject<T, T>
{
    volatile Object lastValue;
    private final NotificationLite<T> nl;
    final SubjectSubscriptionManager<T> state;
    
    protected AsyncSubject(final OnSubscribe<T> onSubscribe, final SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.nl = NotificationLite.instance();
        this.state = state;
    }
    
    public static <T> AsyncSubject<T> create() {
        final SubjectSubscriptionManager<T> subjectSubscriptionManager = (SubjectSubscriptionManager<T>)new SubjectSubscriptionManager<Object>();
        subjectSubscriptionManager.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() {
            @Override
            public void call(final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                final Object latest = subjectSubscriptionManager.getLatest();
                final NotificationLite<T> nl = (NotificationLite<T>)subjectSubscriptionManager.nl;
                if (latest == null || nl.isCompleted(latest)) {
                    subjectObserver.onCompleted();
                    return;
                }
                if (nl.isError(latest)) {
                    subjectObserver.onError(nl.getError(latest));
                    return;
                }
                subjectObserver.actual.setProducer(new SingleProducer<Object>((Subscriber<? super Object>)subjectObserver.actual, nl.getValue(latest)));
            }
        };
        return new AsyncSubject<T>((OnSubscribe<Object>)subjectSubscriptionManager, (SubjectSubscriptionManager<Object>)subjectSubscriptionManager);
    }
    
    public Throwable getThrowable() {
        final Object latest = this.state.getLatest();
        if (this.nl.isError(latest)) {
            return this.nl.getError(latest);
        }
        return null;
    }
    
    public T getValue() {
        final Object lastValue = this.lastValue;
        if (!this.nl.isError(this.state.getLatest()) && this.nl.isNext(lastValue)) {
            return this.nl.getValue(lastValue);
        }
        return null;
    }
    
    public boolean hasCompleted() {
        final Object latest = this.state.getLatest();
        return latest != null && !this.nl.isError(latest);
    }
    
    @Override
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
    
    public boolean hasThrowable() {
        return this.nl.isError(this.state.getLatest());
    }
    
    public boolean hasValue() {
        final Object lastValue = this.lastValue;
        return !this.nl.isError(this.state.getLatest()) && this.nl.isNext(lastValue);
    }
    
    @Override
    public void onCompleted() {
        if (this.state.active) {
            Object o;
            if ((o = this.lastValue) == null) {
                o = this.nl.completed();
            }
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(o);
            for (int length = terminate.length, i = 0; i < length; ++i) {
                final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver = terminate[i];
                if (o == this.nl.completed()) {
                    subjectObserver.onCompleted();
                }
                else {
                    subjectObserver.actual.setProducer(new SingleProducer<Object>((Subscriber<? super Object>)subjectObserver.actual, this.nl.getValue(o)));
                }
            }
        }
    }
    
    @Override
    public void onError(final Throwable t) {
        if (this.state.active) {
            final Object error = this.nl.error(t);
            List<Throwable> list = null;
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(error);
            final int length = terminate.length;
            int i = 0;
        Label_0057_Outer:
            while (i < length) {
                final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver = terminate[i];
                while (true) {
                    try {
                        subjectObserver.onError(t);
                        ++i;
                        continue Label_0057_Outer;
                    }
                    catch (Throwable t2) {
                        List<Throwable> list2 = list;
                        if (list == null) {
                            list2 = new ArrayList<Throwable>();
                        }
                        list2.add(t2);
                        list = list2;
                        continue;
                    }
                    break;
                }
                break;
            }
            Exceptions.throwIfAny(list);
        }
    }
    
    @Override
    public void onNext(final T t) {
        this.lastValue = this.nl.next(t);
    }
}
