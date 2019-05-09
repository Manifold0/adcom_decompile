// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.exceptions.OnErrorNotImplementedException;
import rx.Observer;

public final class Observers
{
    private static final Observer<Object> EMPTY;
    
    static {
        EMPTY = new Observer<Object>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
            
            @Override
            public final void onNext(final Object o) {
            }
        };
    }
    
    private Observers() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Observer<T> create(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return new Observer<T>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
            
            @Override
            public final void onNext(final T t) {
                action1.call(t);
            }
        };
    }
    
    public static <T> Observer<T> create(final Action1<? super T> action1, final Action1<Throwable> action2) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return new Observer<T>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                action2.call(t);
            }
            
            @Override
            public final void onNext(final T t) {
                action1.call(t);
            }
        };
    }
    
    public static <T> Observer<T> create(final Action1<? super T> action1, final Action1<Throwable> action2, final Action0 action3) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (action3 == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }
        return new Observer<T>() {
            @Override
            public final void onCompleted() {
                action3.call();
            }
            
            @Override
            public final void onError(final Throwable t) {
                action2.call(t);
            }
            
            @Override
            public final void onNext(final T t) {
                action1.call(t);
            }
        };
    }
    
    public static <T> Observer<T> empty() {
        return (Observer<T>)Observers.EMPTY;
    }
}
