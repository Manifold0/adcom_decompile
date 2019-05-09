// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeThrow<T> implements OnSubscribe<T>
{
    private final Throwable exception;
    
    public OnSubscribeThrow(final Throwable exception) {
        this.exception = exception;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        subscriber.onError(this.exception);
    }
}
