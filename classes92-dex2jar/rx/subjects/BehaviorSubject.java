// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.util.List;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import java.lang.reflect.Array;
import rx.functions.Action1;
import rx.Observable;
import rx.internal.operators.NotificationLite;

public final class BehaviorSubject<T> extends Subject<T, T>
{
    private static final Object[] EMPTY_ARRAY;
    private final NotificationLite<T> nl;
    private final SubjectSubscriptionManager<T> state;
    
    static {
        EMPTY_ARRAY = new Object[0];
    }
    
    protected BehaviorSubject(final OnSubscribe<T> onSubscribe, final SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.nl = NotificationLite.instance();
        this.state = state;
    }
    
    public static <T> BehaviorSubject<T> create() {
        return create((T)null, false);
    }
    
    public static <T> BehaviorSubject<T> create(final T t) {
        return create(t, true);
    }
    
    private static <T> BehaviorSubject<T> create(final T t, final boolean b) {
        final SubjectSubscriptionManager<T> subjectSubscriptionManager = (SubjectSubscriptionManager<T>)new SubjectSubscriptionManager<Object>();
        if (b) {
            subjectSubscriptionManager.setLatest(NotificationLite.instance().next(t));
        }
        subjectSubscriptionManager.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() {
            @Override
            public void call(final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                subjectObserver.emitFirst(subjectSubscriptionManager.getLatest(), subjectSubscriptionManager.nl);
            }
        };
        subjectSubscriptionManager.onTerminated = subjectSubscriptionManager.onAdded;
        return new BehaviorSubject<T>((OnSubscribe<Object>)subjectSubscriptionManager, (SubjectSubscriptionManager<Object>)subjectSubscriptionManager);
    }
    
    public Throwable getThrowable() {
        final Object latest = this.state.getLatest();
        if (this.nl.isError(latest)) {
            return this.nl.getError(latest);
        }
        return null;
    }
    
    public T getValue() {
        final Object latest = this.state.getLatest();
        if (this.nl.isNext(latest)) {
            return this.nl.getValue(latest);
        }
        return null;
    }
    
    public Object[] getValues() {
        Object[] values;
        if ((values = this.getValues(BehaviorSubject.EMPTY_ARRAY)) == BehaviorSubject.EMPTY_ARRAY) {
            values = new Object[0];
        }
        return values;
    }
    
    public T[] getValues(final T[] array) {
        final Object latest = this.state.getLatest();
        T[] array3;
        if (this.nl.isNext(latest)) {
            Object[] array2 = array;
            if (array.length == 0) {
                array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), 1);
            }
            array2[0] = this.nl.getValue(latest);
            array3 = (T[])array2;
            if (((T[])array2).length > 1) {
                array2[1] = null;
                array3 = (T[])array2;
            }
        }
        else {
            array3 = array;
            if (array.length > 0) {
                array[0] = null;
                return array;
            }
        }
        return array3;
    }
    
    public boolean hasCompleted() {
        return this.nl.isCompleted(this.state.getLatest());
    }
    
    @Override
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
    
    public boolean hasThrowable() {
        return this.nl.isError(this.state.getLatest());
    }
    
    public boolean hasValue() {
        return this.nl.isNext(this.state.getLatest());
    }
    
    @Override
    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            final Object completed = this.nl.completed();
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(completed);
            for (int length = terminate.length, i = 0; i < length; ++i) {
                terminate[i].emitNext(completed, this.state.nl);
            }
        }
    }
    
    @Override
    public void onError(Throwable t) {
        if (this.state.getLatest() == null || this.state.active) {
            final Object error = this.nl.error(t);
            t = null;
            final SubjectSubscriptionManager.SubjectObserver<T>[] terminate = this.state.terminate(error);
            final int length = terminate.length;
            int i = 0;
        Label_0074_Outer:
            while (i < length) {
                final SubjectSubscriptionManager.SubjectObserver<T> subjectObserver = terminate[i];
                while (true) {
                    try {
                        subjectObserver.emitNext(error, this.state.nl);
                        ++i;
                        continue Label_0074_Outer;
                    }
                    catch (Throwable t2) {
                        Object o = t;
                        if (t == null) {
                            o = new ArrayList<Throwable>();
                        }
                        ((List<Throwable>)o).add(t2);
                        t = (Throwable)o;
                        continue;
                    }
                    break;
                }
                break;
            }
            Exceptions.throwIfAny((List<? extends Throwable>)t);
        }
    }
    
    @Override
    public void onNext(final T t) {
        if (this.state.getLatest() == null || this.state.active) {
            final Object next = this.nl.next(t);
            final SubjectSubscriptionManager.SubjectObserver<T>[] next2 = this.state.next(next);
            for (int length = next2.length, i = 0; i < length; ++i) {
                next2[i].emitNext(next, this.state.nl);
            }
        }
    }
    
    int subscriberCount() {
        return this.state.observers().length;
    }
}
