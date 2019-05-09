// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.Observer;
import rx.functions.Action0;
import rx.exceptions.OnErrorNotImplementedException;
import rx.Subscriber;
import rx.functions.Action1;

public final class Subscribers
{
    private Subscribers() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Subscriber<T> create(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return new Subscriber<T>() {
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
    
    public static <T> Subscriber<T> create(final Action1<? super T> action1, final Action1<Throwable> action2) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return new Subscriber<T>() {
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
    
    public static <T> Subscriber<T> create(final Action1<? super T> action1, final Action1<Throwable> action2, final Action0 action3) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (action3 == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }
        return new Subscriber<T>() {
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
    
    public static <T> Subscriber<T> empty() {
        return from(Observers.empty());
    }
    
    public static <T> Subscriber<T> from(final Observer<? super T> observer) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                observer.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                observer.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                observer.onNext(t);
            }
        };
    }
    
    public static <T> Subscriber<T> wrap(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
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
    }
}
