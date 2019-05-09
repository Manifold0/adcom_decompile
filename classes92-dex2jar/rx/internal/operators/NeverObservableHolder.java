// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Observable;

public enum NeverObservableHolder implements OnSubscribe<Object>
{
    INSTANCE;
    
    static final Observable<Object> NEVER;
    
    static {
        NEVER = Observable.create((Observable.OnSubscribe<Object>)NeverObservableHolder.INSTANCE);
    }
    
    public static <T> Observable<T> instance() {
        return (Observable<T>)NeverObservableHolder.NEVER;
    }
    
    @Override
    public void call(final Subscriber<? super Object> subscriber) {
    }
}
